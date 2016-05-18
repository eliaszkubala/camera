import view.EditEvent;
import utility.GlobalData;
import utility.ImageUtility;
import utility.RenderInfoFile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Created by TheKing on 03.04.2016.
 */
public class EventPreviewMain implements ActionListener {

    private static FileMaker fileMaker;
    private static int eventFileID = 0;
    private static String eventProjectName;
    private EventTree inne;
    private EventTree inwe;
    private EventTree rurociag;
    private EventTree studz;
    private EditEvent eventPreview;
    private JPopupMenu defaultMenu;
    private JArc jArc;

    public static void main(String[] args) {
        eventFileID = 0;
        eventProjectName = "test";
        GlobalData.setCurrentProjectDir("Projekt testowy2");
        fileMaker = new FileMaker(eventProjectName, GlobalData.getPathForFileMaker());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EventPreviewMain(args);
            }
        });
    }

    public static void openWindow(String projectName, int eventID) {
        eventProjectName = projectName;
        eventFileID = eventID;

        LogsSender.send(LogsIntCode.EDYTOWANO_ZDARZENIE, LogsString.EDYTOWANO_ZDARZENIE + " " + projectName + ":" + eventFileID);

        fileMaker = new FileMaker(projectName, GlobalData.getPathForFileMaker());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EventPreviewMain(null);
            }
        });
    }

    public EditEvent getGUI() {
        if (eventPreview == null) {
            eventPreview = new EditEvent();
            refreshJArc();
        }
        return eventPreview;
    }

    private void createJArc(int startAngle, int arcAngle) {
        jArc = new JArc(startAngle, arcAngle);
        getGUI().getPanel6().removeAll();
        jArc.setPreferredSize(new Dimension(100, 100));
        getGUI().getPanel6().add(jArc, BorderLayout.CENTER);
        getGUI().getPanel6().updateUI();
    }

    public EventPreviewMain(String[] args) {
        prepareData();
        getGUI();

        String info = new RenderInfoFile(GlobalData.getPathForFileMaker(getFileName(eventProjectName, eventFileID))).getString();
        new CustomSerialize(getGUI().getPanel()).set(info);
        refreshJArc();

        addListeners();
        initialize();
    }

    public static String getFileName(String eventProjectName, int eventFileID) {
        return eventProjectName + "-event_" + eventFileID + ".txt";
    }

    private void initialize() {
        String path = GlobalData.getCurrentProjectFile() +"\\" + getSnapshotName(eventProjectName, eventFileID);
        getGUI().getZdjecie().setIcon(ImageUtility.rescaleImage(new File(path), 800, 600));

        defaultRadioButtonInit();

        JTextField[] fields = new JTextField[3];
        fields[0] = getGUI().getOpis1();
        fields[1] = getGUI().getOpis2();
        fields[2] = getGUI().getOpis3();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setVisible(!fields[i].getText().equals(""));
        }
    }

    private void defaultRadioButtonInit() {
        JRadioButton[] radio = new JRadioButton[4];
        radio[0] = getGUI().getRadioButton1();
        radio[1] = getGUI().getRadioButton2();
        radio[2] = getGUI().getRadioButton3();
        radio[3] = getGUI().getRadioButton4();

        Boolean selected = false;
        for(int i=0; i<radio.length; i++) {
            selected = radio[i].isSelected();
            if(selected == true) {
                defualtMenuInit(radio[i].getName());
                break;
            }
        }
        if(!selected){
            defaultMenu = rurociag.getMenu(this);
            getGUI().getRadioButton1().setSelected(true);
        }
    }

    private void addListeners() {
        getGUI().getCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGUI().setVisible(false);
            }
        });

        getGUI().getSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = new CustomSerialize(getGUI().getPanel()).get();
                fileMaker.createEventFile(eventFileID, string);
                getGUI().setVisible(false);
                Project.getInstance().currentProjectPreview.refreshData();
            }
        });

        getGUI().getRadioButton1().addActionListener(this);
        getGUI().getRadioButton2().addActionListener(this);
        getGUI().getRadioButton3().addActionListener(this);
        getGUI().getRadioButton4().addActionListener(this);
        getGUI().getSelectEvent().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                defaultMenu.show(e.getComponent(), e.getX(), e.getY());
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshJArc();
            }
        };

        getGUI().getSzerokosc().addChangeListener(sliderListener);
        getGUI().getPrzesuniecie().addChangeListener(sliderListener);

    }

    private void refreshJArc() {
        createJArc(getGUI().getPrzesuniecie().getValue(), getGUI().getSzerokosc().getValue());
    }

    public void prepareData() {
        rurociag = new EventTree();
        rurociag.addOld("BAA - Deformacja", "A - pionowa - wysokość przewodu została zredukowana", new String[]{});
        rurociag.addOld("BAA - Deformacja", "B - pozioma - szerokość przewodu została zredukowana", new String[]{});

        rurociag.addOld("BAB - Szczelina", "A - zarysowanie", new String[]{"A - wzdłużna", "B - obwodowe", "C - złożone", "D - spiralne"});
        rurociag.addOld("BAB - Szczelina", "B - pęknięcie", new String[]{"A - wzdłużna", "B - obwodowe", "C - złożone", "D - spiralne"});
        rurociag.addOld("BAB - Szczelina", "C - złamanie", new String[]{"A - wzdłużna", "B - obwodowe", "C - złożone", "D - spiralne"});

        rurociag.addOld("BAC - Przerwanie/Zapadnięcie", "A - przerwanie", new String[]{});
        rurociag.addOld("BAC - Przerwanie/Zapadnięcie", "B - brakujące", new String[]{});
        rurociag.addOld("BAC - Przerwanie/Zapadnięcie", "C - zapadnięcie się", new String[]{});

        rurociag.addOld("BAD - Wadliwe roboty murarskie lub kamieniarskie", "A - przemieszczone", new String[]{"A - kolejna warstwa ułożonych cegieł lub kamiena jest widoczna", "B - nic nie jest widoczne"});
        rurociag.addOld("BAD - Wadliwe roboty murarskie lub kamieniarskie", "B - brakujące", new String[]{"A - kolejna warstwa ułożonych cegieł lub kamiena jest widoczna", "B - nic nie jest widoczne"});
        rurociag.addOld("BAD - Wadliwe roboty murarskie lub kamieniarskie", "D - zapadnięcie się", new String[]{"A - kolejna warstwa ułożonych cegieł lub kamiena jest widoczna", "B - nic nie jest widoczne"});

        rurociag.addOld("BAE - Brakująca zaprawa", null, null);

        String[] BAFpod = new String[]{"A - działanie mechaniczne", "B - działanie chemiczne - ogólnie", "C - działanie chemiczne - zniszczenie powyżej poziomu ścieków", "D - działanie chemiczne - zniszczenie poniżej poziomu ścieków", "E - przyczyna nie jest jednoznaczna"};
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "A - zwiększona chropowatość", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "B - wykruszanie się", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "C - widoczne kruszywo", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "D - kruszywo wystające z powierchni", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "E - brakujące kruszywo", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "F - widoczne zbrojenie", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "G - zbrojenie wystające z powierchni", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "H - skorodowane zbrojenie", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "I - brakująca ściana", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "J - produkty korozji na powierzchni", BAFpod);
        rurociag.addOld("BAF - Uszkodzenie powierzchniowe", "Z - inne uszkodzenie powierzchniowe", BAFpod);

        rurociag.addOld("BAG - Połączenie wystające", null, null);

        rurociag.add("BAH - Wadliwe połączenie", "A - umiejscowienie połączenia jest nieprawidłowe");
        rurociag.add("BAH - Wadliwe połączenie", "B - obecna jest przerwa");
        rurociag.add("BAH - Wadliwe połączenie", "C - obecna jest częściowa przerwa");
        rurociag.add("BAH - Wadliwe połączenie", "D - przewód przyłączeniowy jest uszkodzony");
        rurociag.add("BAH - Wadliwe połączenie", "E - przewód przyłączeniowy jest zablokowany");
        rurociag.add("BAH - Wadliwe połączenie", "Z - inne");

        rurociag.addOld("BAI - Wystające uszczelnienie", "A - pierścień uszczelniający", new String[]{"A - widoczne przemieszczona, ale nie wystająca", "B - wisząca, ale nie wystająca", "C - wisząca, ale nie złamana", "D - złamana"});
        rurociag.addOld("BAI - Wystające uszczelnienie", "Z - inne materiały uszczelniające", new String[]{"A - widoczne przemieszczona, ale nie wystająca", "B - wisząca, ale nie wystająca", "C - wisząca, ale nie złamana", "D - złamana"});

        rurociag.add("BAJ - Przemieszczone złącze", "A - wzdłużne");
        rurociag.add("BAJ - Przemieszczone złącze", "B - promieniste");
        rurociag.add("BAJ - Przemieszczone złącze", "C - pod kątem");

        rurociag.addOld("BAK - Wada wykładziny", "A - wykładzina jest oderwana", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        rurociag.addOld("BAK - Wada wykładziny", "B - zmiana barwy wykładziny", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        rurociag.addOld("BAK - Wada wykładziny", "C - wadliwa końcówka wykładziny", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        rurociag.addOld("BAK - Wada wykładziny", "D - pomarszczona wykładzina", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        rurociag.addOld("BAK - Wada wykładziny", "E - wykładzina z pęcherzami", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        rurociag.addOld("BAK - Wada wykładziny", "Z - inne wady wykładziny", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});

        rurociag.add("BAL - Wadliwa naprawa", "A - brak części ściany");
        rurociag.add("BAL - Wadliwa naprawa", "B - łata wypełniająca otwór celowo wykonana w ścianie przewodu");
        rurociag.add("BAL - Wadliwa naprawa", "Z - inne");

        rurociag.add("BAM - Uszkodzenie spoiny", "A - wzdłużne");
        rurociag.add("BAM - Uszkodzenie spoiny", "B - obwodowe");
        rurociag.add("BAM - Uszkodzenie spoiny", "C - spiralne");

        rurociag.add("BAN - Przewód porowaty");

        rurociag.add("BAO - Grunt widoczny przez wadę");

        rurociag.add("BAP - Pusta przestrzeń widoczna przez wadę");

        rurociag.add("BBA - Korzenie", "A - korzeń zbity");
        rurociag.add("BBA - Korzenie", "B - niezależne drobne korzenie");
        rurociag.add("BBA - Korzenie", "C - złożona masa korzeni");

        rurociag.add("BBB - Przyczepione osady", "A - inkrustracja");
        rurociag.add("BBB - Przyczepione osady", "B - tłuszcz");
        rurociag.add("BBB - Przyczepione osady", "C - śluzowaty osad");
        rurociag.add("BBB - Przyczepione osady", "Z - inne");

        rurociag.add("BBC - Odłożone osady", "A - drobnoziarnisty");
        rurociag.add("BBC - Odłożone osady", "B - gruboziarnisty");
        rurociag.add("BBC - Odłożone osady", "C - twardy lub zbity materiał");
        rurociag.add("BBC - Odłożone osady", "Z - inne");

        rurociag.add("BBD - Wpadanie gruntu", "A - piach");
        rurociag.add("BBD - Wpadanie gruntu", "B - torf");
        rurociag.add("BBD - Wpadanie gruntu", "C - materiał drobnoziarnisty");
        rurociag.add("BBD - Wpadanie gruntu", "D - żwir");
        rurociag.add("BBD - Wpadanie gruntu", "Z - inne");

        rurociag.add("BBE - Inne przeszkody", "A - zerwana cegła lub element kamieniarski leżący na dnie");
        rurociag.add("BBE - Inne przeszkody", "B - torf");
        rurociag.add("BBE - Inne przeszkody", "C - inny obiekt leżący na dnie");
        rurociag.add("BBE - Inne przeszkody", "D - wystający ze ściany");
        rurociag.add("BBE - Inne przeszkody", "E - zaklinowany w złączu");
        rurociag.add("BBE - Inne przeszkody", "F - wchodząca przez połączenie/przewód łącznikowy");
        rurociag.add("BBE - Inne przeszkody", "G - zewnętrzne przewody lub kable przechodzące przez rurociąg");
        rurociag.add("BBE - Inne przeszkody", "H - wbudowane w strukturę");
        rurociag.add("BBE - Inne przeszkody", "Z - inne");

        rurociag.add("BBF - Infiltracja", "A - pocenie");
        rurociag.add("BBF - Infiltracja", "B - kapanie");
        rurociag.add("BBF - Infiltracja", "C - płynący");
        rurociag.add("BBF - Infiltracja", "D - tryskający");

        rurociag.add("BBG - Eksfiltracja");

        rurociag.add("BBH - Szkodniki i insekty", "A - szczury", "A - w rurociągu", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
        rurociag.add("BBH - Szkodniki i insekty", "B - karaluchy", "A - w rurociągu", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
        rurociag.add("BBH - Szkodniki i insekty", "Z - inne", "A - w rurociągu", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");


        inwe = new EventTree();
        inwe.add("BCA - Połączenie", "A - łącznik", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        inwe.add("BCA - Połączenie", "B - połączenie siodłowe - wywiercone", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        inwe.add("BCA - Połączenie", "C - połączenie siodłowe - wydłutowane", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        inwe.add("BCA - Połączenie", "D - proste połączenie - wywiercone", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        inwe.add("BCA - Połączenie", "E - proste połączenie - wydłutowane", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        inwe.add("BCA - Połączenie", "F - połączenie inne niż łącznik", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        inwe.add("BCA - Połączenie", "G - rodzaj połączenia nieokreślony", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        inwe.add("BCA - Połączenie", "Z - inny rodzaj połączenia", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");

        inwe.add("BCB - Naprawa punktowa", "A - wymieniony przewód");
        inwe.add("BCB - Naprawa punktowa", "B - miejscowa wykładzina");
        inwe.add("BCB - Naprawa punktowa", "C - wstrzyknięta zaprawa cementowa");
        inwe.add("BCB - Naprawa punktowa", "D - inne wstrzyknięte materiały uszczelniające");
        inwe.add("BCB - Naprawa punktowa", "E - załatany otwór");
        inwe.add("BCB - Naprawa punktowa", "Z - inne bezwykopowe metody naprawy");


        inwe.add("BCC - Krzywizna kanału", "A - lewy", "A - góra", "B - dół");
        inwe.add("BCC - Krzywizna kanału", "B - prawy", "A - góra", "B - dół");

        inwe.add("BCD - Węzeł początkowy", "A - studzienka włazowa");
        inwe.add("BCD - Węzeł początkowy", "B - studzienka niewłazowa");
        inwe.add("BCD - Węzeł początkowy", "C - podłączenie do kanałów ulicznych");
        inwe.add("BCD - Węzeł początkowy", "D - otwór na lampę");
        inwe.add("BCD - Węzeł początkowy", "E - wylot");
        inwe.add("BCD - Węzeł początkowy", "F - ważne połączenia bez studzienki włazowej lub studzienki niewłazowej");
        inwe.add("BCD - Węzeł początkowy", "XA - rodzaj zdefiniowany przez władze eksploatacyjne");
        inwe.add("BCD - Węzeł początkowy", "Z - inne specjalne studzienki");


        inwe.add("BCE - Węzeł końcowy", "A - studzienka włazowa");
        inwe.add("BCE - Węzeł końcowy", "B - studzienka niewłazowa");
        inwe.add("BCE - Węzeł końcowy", "C - podłączenie do kanałów ulicznych");
        inwe.add("BCE - Węzeł końcowy", "D - otwór na lampę");
        inwe.add("BCE - Węzeł końcowy", "E - wylot");
        inwe.add("BCE - Węzeł końcowy", "F - ważne połączenia bez studzienki włazowej lub studzienki niewłazowej");
        inwe.add("BCE - Węzeł końcowy", "XA - rodzaj zdefiniowany przez władze eksploatacyjne");
        inwe.add("BCE - Węzeł końcowy", "Z - inne specjalne studzienki");

        inwe.add("DCA - Rodzaj połączenia", "A - połączenie w kinecie");
        inwe.add("DCA - Rodzaj połączenia", "B - swobodny spadek do kinety");
        inwe.add("DCA - Rodzaj połączenia", "C - kaskadowe");
        inwe.add("DCA - Rodzaj połączenia", "D - z rurą wewnętrzną");
        inwe.add("DCA - Rodzaj połączenia", "E - połączenie wyniesione nad kinetę");
        inwe.add("DCA - Rodzaj połączenia", "F - przewód wentylacyjny");
        inwe.add("DCA - Rodzaj połączenia", "Z - inne");

        inwe.add("DCB - Naprawa punktowa", "A - wymieniono część ścianki");
        inwe.add("DCB - Naprawa punktowa", "B - miejscowa wykładzina");
        inwe.add("DCB - Naprawa punktowa", "C - wstrzyknięty materiał uszczelniający");
        inwe.add("DCB - Naprawa punktowa", "D - inne");

        inwe.add("DCG - Połączeniowy (boczny) rurociąg", "A - kołowy", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        inwe.add("DCG - Połączeniowy (boczny) rurociąg", "B - prostokątny", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        inwe.add("DCG - Połączeniowy (boczny) rurociąg", "C - jajowy", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        inwe.add("DCG - Połączeniowy (boczny) rurociąg", "D - w kształcie litery U", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        inwe.add("DCG - Połączeniowy (boczny) rurociąg", "E - łukowaty", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        inwe.add("DCG - Połączeniowy (boczny) rurociąg", "F - owalny", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        inwe.add("DCG - Połączeniowy (boczny) rurociąg", "X - lokalny odcinek zdefiniowany przez władze eksploatującą", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        inwe.add("DCG - Połączeniowy (boczny) rurociąg", "Z - inne", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");

        inwe.add("DCH - Spocznik", "A - spocznik uszkodzony");
        inwe.add("DCH - Spocznik", "B - spocznik nieuszkodzony");

        inwe.add("DCI - Kineta", "A - kineta uszkodzona");
        inwe.add("DCI - Kineta", "B - kineta nieuszkodzona");

        inwe.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "A - obecny jest łańcuch bezpieczeństwa bez uszkodzeń");
        inwe.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "B - brakuje łańcucha bezpieczeństwa");
        inwe.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "C - uszkodzony łańcuch bezpieczeństwa");
        inwe.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "D - łańcuch bezpieczeństwa znajduje się na swoim położeniu, ale jest pokryty zanieczyszczeniami");
        inwe.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "E - uchwyty obecne bez uszkodzeń");
        inwe.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "F - brakuje uchwytów bezpieczeństwa");
        inwe.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "G - uszkodzone uchwyty bezpieczeństwa");
        inwe.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "H - uchwyty bezpieczeństwa znajdują się na swoim położeniu, ale są pokryte zanieczyszczeniami");

        inwe.add("DCK - Kontrola przepływu", "A - przelew", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "B - syfon", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "C - kryza pomiarowa", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "D - przepływomierz typu vortex", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "E - zasuwa klapowa", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "F - zasuwa sterowana płynnie", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "G - zwężka pomiarowa", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "H - zawór klapowy", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "I - kraty", "A - przepływ ciągły", "B - przelewy");
        inwe.add("DCK - Kontrola przepływu", "Z - inne", "A - przepływ ciągły", "B - przelewy");

        inwe.add("DCL - Uszczelniona rura przechodząca przez studzienkę", "A - brak dostępu do przewodu", "A - uszkodzony", "B - nieuszkodzony");
        inwe.add("DCL - Uszczelniona rura przechodząca przez studzienkę", "B - dostęp jest zapewniony - pokrywa na miejscu", "A - uszkodzony", "B - nieuszkodzony");
        inwe.add("DCL - Uszczelniona rura przechodząca przez studzienkę", "C - zapewniony dostęp - brak pokrywy", "A - uszkodzony", "B - nieuszkodzony");

        inwe.add("DCM - Kosz osadowy", "A - obecny kosz niewadliwy");
        inwe.add("DCM - Kosz osadowy", "B - brakujący kosz");
        inwe.add("DCM - Kosz osadowy", "C - wadliwy kosz");

        inwe.add("DCN - Osadnik", "A - osadnik niewadliwy");
        inwe.add("DCN - Osadnik", "B - osadnik wadliwy");

        inwe.add("DCO - Przekrój poprzeczny", "A - kołowy");
        inwe.add("DCO - Przekrój poprzeczny", "B - prostokątny");
        inwe.add("DCO - Przekrój poprzeczny", "XA - przekrój lokalny");
        inwe.add("DCO - Przekrój poprzeczny", "Z - inne");


        inne = new EventTree();
        inne.add("BDA - Zdjęcia ogólne");

        inne.add("BDB - Uwaga ogólna");

        inne.add("BDC - Przerwana inspekcja", "A - przeszkoda");
        inne.add("BDC - Przerwana inspekcja", "B - wysoki poziom ścieków");
        inne.add("BDC - Przerwana inspekcja", "C - awaria sprzętu");
        inne.add("BDC - Przerwana inspekcja", "Z - inne");

        inne.add("BDD - Poziom ścieków", "A - przejrzyste (dno jest widoczne)");
        inne.add("BDD - Poziom ścieków", "B - mętne lub barwne");

        inne.add("BDE - Przepływ w przewodzie podłączonym", "A - przejrzyste (dno jest widoczne)", "A - źle podłączony, ponieważ ścieki odpływają do przewodu odpływowego wody powierzchniowej lub kanału", "B - źle podłączony, ponieważ obserwowana woda powierzchniowa odpływa do ściekowego przewodu lub kanału", "C - nie zaobserwowano, że źle podłączony");
        inne.add("BDE - Przepływ w przewodzie podłączonym", "B - mętne lub barwne", "A - źle podłączony, ponieważ ścieki odpływają do przewodu odpływowego wody powierzchniowej lub kanału", "B - źle podłączony, ponieważ obserwowana woda powierzchniowa odpływa do ściekowego przewodu lub kanału", "C - nie zaobserwowano, że źle podłączony");
        inne.add("BDE - Przepływ w przewodzie podłączonym", "YY - poziom ścieków w głównym przewodzie za wysoki", "A - źle podłączony, ponieważ ścieki odpływają do przewodu odpływowego wody powierzchniowej lub kanału", "B - źle podłączony, ponieważ obserwowana woda powierzchniowa odpływa do ściekowego przewodu lub kanału", "C - nie zaobserwowano, że źle podłączony");

        inne.add("BDF - Atmosfera wewnątrz rurociągu", "A - niedobór tlenu");
        inne.add("BDF - Atmosfera wewnątrz rurociągu", "B - siarkowodór");
        inne.add("BDF - Atmosfera wewnątrz rurociągu", "C - metan");
        inne.add("BDF - Atmosfera wewnątrz rurociągu", "Z - inne");

        inne.add("BDG - Utrata widoczności", "A - kamera jest pod wodą");
        inne.add("BDG - Utrata widoczności", "B - szlam");
        inne.add("BDG - Utrata widoczności", "C - para");
        inne.add("BDG - Utrata widoczności", "Z - inne");

        inne.add("DDA - Zdjęcie ogólne");

        inne.add("DDB - Uwaga ogólna");

        inne.add("DDC - Przerwana inspekcja", "A - niemożność podniesienia pokrywy");
        inne.add("DDC - Przerwana inspekcja", "B - zablokowanie");
        inne.add("DDC - Przerwana inspekcja", "C - wysoki poziom ścieków");
        inne.add("DDC - Przerwana inspekcja", "D - awaria sprzętu");
        inne.add("DDC - Przerwana inspekcja", "Z - inne");

        inne.add("DDE - Przepływ we wchodzącym przewodzie", "A - przejrzyste ścieki", "A - źle podłączony, ścieki odprowadzane są do przewodów kanalizacji deszczowej lub systemu ściekowego", "B - źle podłączone, wody powierzchniowe odprowadzane są do przewodu ściekowego lub kolektora", "C - nie obserwuje się złego podłączenia");
        inne.add("DDE - Przepływ we wchodzącym przewodzie", "B - mętne lub zabarwione ścieki", "A - źle podłączony, ścieki odprowadzane są do przewodów kanalizacji deszczowej lub systemu ściekowego", "B - źle podłączone, wody powierzchniowe odprowadzane są do przewodu ściekowego lub kolektora", "C - nie obserwuje się złego podłączenia");
        inne.add("DDE - Przepływ we wchodzącym przewodzie", "YY - przepływ w przewodzie przyłączeniowym jest niewidoczny", "A - źle podłączony, ścieki odprowadzane są do przewodów kanalizacji deszczowej lub systemu ściekowego", "B - źle podłączone, wody powierzchniowe odprowadzane są do przewodu ściekowego lub kolektora", "C - nie obserwuje się złego podłączenia");

        inne.add("DDF - Atmosfera w komorze", "A - niedobór tlenu");
        inne.add("DDF - Atmosfera w komorze", "B - siarkowodór");
        inne.add("DDF - Atmosfera w komorze", "C - metan");
        inne.add("DDF - Atmosfera w komorze", "Z - inne");

        inne.add("DDG - Utrata widoczności", "A - kamera znajduje się pod ściekami");
        inne.add("DDG - Utrata widoczności", "B - szlam");
        inne.add("DDG - Utrata widoczności", "C - para");
        inne.add("DDG - Utrata widoczności", "Z - inne");


        studz = new EventTree();

        studz.add("DAA - Deformacja", "A - ogólna");
        studz.add("DAA - Deformacja", "B - zlokalizowana");

        studz.add("DAB - Szczelina", "A - zarysowanie", "A - pionowa", "B - pozioma", "C - złożona", "D - pochyłe");
        studz.add("DAB - Szczelina", "B - pęknięcie", "A - pionowa", "B - pozioma", "C - złożona", "D - pochyłe");
        studz.add("DAB - Szczelina", "C - załamanie", "A - pionowa", "B - pozioma", "C - złożona", "D - pochyłe");

        studz.add("DAC - Przerwanie/Zapadnięcie się", "A - przerwanie");
        studz.add("DAC - Przerwanie/Zapadnięcie się", "B - brakujące");
        studz.add("DAC - Przerwanie/Zapadnięcie się", "C - zapadnięcie się");

        studz.add("DAD - Wadliwe roboty murarskie lub kamieniarskie",
                "A - przemieszczone",
                "A - kolejna warstwa cegieł albo kamienia jest widoczna", "B - nic nie jest widoczne");

        studz.add("DAD - Wadliwe roboty murarskie lub kamieniarskie",
                "B - brakujące",
                "A - kolejna warstwa cegieł albo kamienia jest widoczna", "B - nic nie jest widoczne");

        studz.add("DAD - Wadliwe roboty murarskie lub kamieniarskie",
                "C - zapadnięcie się",
                "A - kolejna warstwa cegieł albo kamienia jest widoczna", "B - nic nie jest widoczne");

        studz.add("DAE - Brakująca zaprawa");

        studz.add("DAF - Uszkodzenie powierzchniowe", "A - zwiększona chropowatość", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "B - wykruszanie się", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "C - widoczne kruszywo", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "D - kruszywo wystające z powierzchni", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "E - brakujące kruszywo", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "F - widoczne zbrojenie", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "G - zbrojenie wystające z powierzchni", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "H - skorodowane zbrojenie", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "I - brakująca ściana", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "J - produkty korozji na powierzchni", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "K - działanie chemiczne - działanie biochemiczne", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "L - działanie chemiczne - działanie ścieków", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "M - przyczyna nie jest jednoznaczna", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        studz.add("DAF - Uszkodzenie powierzchniowe", "Z - inne uszkodzenia powierzchniowe", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");

        studz.add("DAG - Zakłócające połączenie");

        studz.add("DAH - Wadliwe połączenie", "A - umiejscowienie połączenia jest nieprawidłowe");
        studz.add("DAH - Wadliwe połączenie", "B - obecna jest przerwa pomiędzy końcem przewodu a ścianą studzienki");
        studz.add("DAH - Wadliwe połączenie", "C - obecna jest częściowa przerwa pomiędzy kończem przewodu a ścianą studzienki");
        studz.add("DAH - Wadliwe połączenie", "D - przewód przyłączeniowy jest uszkodzony");
        studz.add("DAH - Wadliwe połączenie", "E - przewód przyłączeniowy jest zablokowany");
        studz.add("DAH - Wadliwe połączenie", "Z - inne");

        studz.add("DAI - Wystające uszczelnienie", "A - pierścień uszczelniający", "A - widocznie przemieszczona, ale nie wystająca do komory", "B - wisząca, ale nie złamana");
        studz.add("DAI - Wystające uszczelnienie", "Z - inne materiały uszczelniające", "A - widocznie przemieszczona, ale nie wystająca do komory", "B - wisząca, ale nie złamana");
        studz.add("DAI - Wystające uszczelnienie", "C - złamana", "A - widocznie przemieszczona, ale nie wystająca do komory", "B - wisząca, ale nie złamana");

        studz.add("DAJ - Przemieszczone złącze", "A - pionowe");
        studz.add("DAJ - Przemieszczone złącze", "B - poziomo");
        studz.add("DAJ - Przemieszczone złącze", "C - pod kątem");


        studz.add("DAK - Wada wykładziny", "A - wykładzina jest oderwana", "A - pionowy", "B - poziomy");
        studz.add("DAK - Wada wykładziny", "B - zmiana barwy wykładziny", "A - pionowy", "B - poziomy");
        studz.add("DAK - Wada wykładziny", "C - wadliwa końcówka wykładziny", "A - pionowy", "B - poziomy");
        studz.add("DAK - Wada wykładziny", "D - pomarszczona wykładzina", "A - pionowy", "B - poziomy");
        studz.add("DAK - Wada wykładziny", "E - wykładzina z pęcherzami", "A - pionowy", "B - poziomy");
        studz.add("DAK - Wada wykładziny", "Z - inne wady wykładziny", "A - pionowy", "B - poziomy");
        studz.add("DAK - Wada wykładziny", "C - złożony", "A - pionowy", "B - poziomy");

        studz.add("DAL - Wadliwa naprawa", "A - brak części ściany");
        studz.add("DAL - Wadliwa naprawa", "B - łata wypełniająca otwór celowo wykonana w ścianie jest teraz wadliwa");
        studz.add("DAL - Wadliwa naprawa", "Z - inne");

        studz.add("DAM - Uszkodzenie spoiny", "A - poziome");
        studz.add("DAM - Uszkodzenie spoiny", "B - pionowe");
        studz.add("DAM - Uszkodzenie spoiny", "C - pochyłe");

        studz.add("DAN - Ściana porowata");

        studz.add("DAO - Grunt widoczny przez wadę");

        studz.add("DAP - Pusta przestrzeń widoczna przez wadę");

        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "A - obluzowany stopień włazowy");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "B - brakujący stopień złazowy");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "C - stopień złazowy jest skorodowany");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "D - stopień złazowy jest zgięty");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "E - plastikowa powłoka stopnia włazowego jest połamana");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "F - poręcz drabiny włazowej jest skorodowana");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "G - podpora drabiny włazowej jest obluzowana");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "H - brakująca podpora drabiny włazowej");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "I - podpora drabiny włazowej jest skorodowana");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "J - stopień drabiny skorodowany");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "K - wadliwy otwór na palce stopy");
        studz.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "Z - inne");

        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "A - złamana pokrywa włazu");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "B - kołysająca się pokrywa włazu");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "C - brakująca pokrywa włazu");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "D - złamana rama");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "E - obluzowana rama");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "F - brakująca rama");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "G - pokrywa włazu poniżej poziomu powierzchni");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "H - pokrywa włazu powyżej poziomu powierzchni");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "I - podpora drabiny włazowej jest skorodowana");
        studz.add("DAR - Wadliwa pokrywa włazu lub rama", "Z - inne");

        studz.add("DBA - Korzenie", "A - korzeń zbity");
        studz.add("DBA - Korzenie", "B - niezależne drobne korzenie");
        studz.add("DBA - Korzenie", "C - złożona masa korzeni");

        studz.add("DBB - Przyczepione osady", "A - inkrustracja");
        studz.add("DBB - Przyczepione osady", "B - tłuszcz");
        studz.add("DBB - Przyczepione osady", "C - śluzowaty osad");
        studz.add("DBB - Przyczepione osady", "Z - inne");

        studz.add("DBC - Odłożone osady", "A - drobnoziarnisty");
        studz.add("DBC - Odłożone osady", "B - gruboziarnisty");
        studz.add("DBC - Odłożone osady", "C - twardy lub zbity materiał");
        studz.add("DBC - Odłożone osady", "Z - inne");

        studz.add("DBD - Wpadanie gruntu");

        studz.add("DBE - Inne przeszkody", "A - zerwana cegła lub element kamieniarski");
        studz.add("DBE - Inne przeszkody", "B - kawałki połamanego przewodu leżące na dnie");
        studz.add("DBE - Inne przeszkody", "C - inny obiekt leżący na dnie");
        studz.add("DBE - Inne przeszkody", "D - wystający ze ściany");
        studz.add("DBE - Inne przeszkody", "E - zaklinowany w złączy");
        studz.add("DBE - Inne przeszkody", "F - wychodząca przez połączenie/przewód łącznikowy");
        studz.add("DBE - Inne przeszkody", "G - zewnętrzne przewody lub kable przechodzące przez strukturę");
        studz.add("DBE - Inne przeszkody", "H - wbudowane w strukturę");
        studz.add("DBE - Inne przeszkody", "Z - inne");

        studz.add("DBF - Infiltracja", "A - pocenie", "A - przez ścianę studzienki włazowej lub studzienki niewłazowej", "B - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki na poziomie dna", "C - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki ponad spocznikiem");
        studz.add("DBF - Infiltracja", "B - kapanie", "A - przez ścianę studzienki włazowej lub studzienki niewłazowej", "B - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki na poziomie dna", "C - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki ponad spocznikiem");
        studz.add("DBF - Infiltracja", "C - płynący", "A - przez ścianę studzienki włazowej lub studzienki niewłazowej", "B - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki na poziomie dna", "C - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki ponad spocznikiem");
        studz.add("DBF - Infiltracja", "D - tryskający", "A - przez ścianę studzienki włazowej lub studzienki niewłazowej", "B - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki na poziomie dna", "C - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki ponad spocznikiem");

        studz.add("DBG - Eksfiltracja");

        studz.add("DBH - Szkodniki i insekty", "A - szczury", "A - w studzience włazowej lub studzience niewłazowej", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
        studz.add("DBH - Szkodniki i insekty", "B - karaluchy", "A - w studzience włazowej lub studzience niewłazowej", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
        studz.add("DBH - Szkodniki i insekty", "Z - inne", "A - w studzience włazowej lub studzience niewłazowej", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton o = (JRadioButton) e.getSource();
        String name = o.getName();
        System.out.println(name);

        defualtMenuInit(name);
    }

    public void defualtMenuInit(String name) {
        if (name.equals("rurociag")) {
                defaultMenu = rurociag.getMenu(this);
            } else if (name.equals("inne")) {
                defaultMenu = inne.getMenu(this);
        }
        if (name.equals("studzienka")) {
            defaultMenu = studz.getMenu(this);
        } else if (name.equals("inwentaryzacja")) {
            defaultMenu = inwe.getMenu(this);
        }
    }

    public void passActionPerformed(String... args) {

        JTextField[] fields = new JTextField[3];
        fields[0] = getGUI().getOpis1();
        fields[1] = getGUI().getOpis2();
        fields[2] = getGUI().getOpis3();

        for (int i = 0; i < fields.length; i++) fields[i].setText("");

        for (int i = 0; i < fields.length; i++) {
            if (args.length >= i + 1) {
                fields[i].setVisible(true);
                fields[i].setText(args[i]);
            } else {
                fields[i].setVisible(false);
            }
        }
        getGUI().getPanel3().updateUI();

    }

    public static String getSnapshotName(String currentFileName, int second) {
        return currentFileName + "event_"+second+"-snapshot.png";
    }
}
