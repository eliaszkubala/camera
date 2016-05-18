import utility.GlobalData;
import view.CameraPreview;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import view.ProjectPreview;

import java.awt.*;

/**
 * Created by TheKing on 24.02.2016.
 */
public abstract class CameraRecord implements CameraRecordInterface {
    private EmbeddedMediaPlayer mediaPlayer;
    private MediaPlayerFactory factory;
    private CanvasVideoSurface videoSurface;
    private Canvas canvas;
    private CameraPreview cameraPreview;

    public CameraPreview getGUI() {
        if (cameraPreview == null)
            cameraPreview = new CameraPreview();
        return cameraPreview;
    }

    public EmbeddedMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void reloadCamera() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if (factory != null) {
            factory.release();
        }
        onReloadCamera();
        createCameraCanvas();
        createGUI();
    }

    private void createCameraCanvas() {
        beforeCameraCreate();
        canvas = new Canvas();
        factory = new MediaPlayerFactory();
        mediaPlayer = factory.newEmbeddedMediaPlayer();
        videoSurface = factory.newVideoSurface(canvas);
        mediaPlayer.setVideoSurface(videoSurface);
    }

    private void createGUI() {
        if (getGUI().getCameraPanel().getComponents().length > 0)
            getGUI().getCameraPanel().remove(0);

        getGUI().getCameraPanel().add(canvas);
        afterCreateGUI();
    }

    public void startPreview(){
        String[] options = {
                ":file-caching=0",
                ":network-caching=250",
                ":no-sout-rtp-sap",
                ":no-sout-standard-sap",
                ":sout-all",
                ":rtsp-caching=100",
                ":sout-keep"};

        getMediaPlayer().playMedia(GlobalData.RTSP_CAMERA_ADDRESS, options);
    }

    public void startNewRecord(){
        GlobalData.createNewInspection();

        String[] optionsRecord = {
                ":network-caching=250",
                "dshow://",
                ":sout=#transcode{}:duplicate{dst=display,dst=std{access=file{no-overwrite},mux=mp4,dst='" + GlobalData.getInspectionPath() + "'}}"
        };

        getMediaPlayer().playMedia(GlobalData.RTSP_CAMERA_ADDRESS, optionsRecord);
        LogsSender.send(LogsIntCode.ROZPOCZETO_INSPEKCJE, LogsString.ROZPOCZETO_INSPEKCJE + " " + GlobalData.getCurrentProjectName() + ":" + GlobalData.getLastRecordFileName());

        System.out.println(GlobalData.getCurrentProjectName() + ":" + GlobalData.getLastRecordFileName());

        fileMaker = new FileMaker(GlobalData.getInspectionName(), GlobalData.getProjectPath());

        fileMaker.setCurrentTimeControl(getGUI().getCurrentRecordTime());
        fileMaker.startTimer();
    }

    private FileMaker fileMaker;
    public FileMaker getFileMaker(){
        return fileMaker;
    }

    public void releaseFilesMaker() {
        if (getFileMaker() != null) getFileMaker().release();
    }

}
