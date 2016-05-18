import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.simple.JSONObject;
import utility.FileUtils;
import utility.RenderInfoFile;

import javax.swing.*;


public class PDFCreator {
    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
            BaseFont.CP1250, BaseFont.EMBEDDED);
    BaseFont bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD,
            BaseFont.CP1250, BaseFont.EMBEDDED);
    Font normal = new Font(bf, 10, Font.NORMAL);
    Font bold = new Font(bfBold, 10, Font.BOLD);
    Font title = new Font(bf, 16, Font.NORMAL);
    private int calledInsideCount = 0;

    public PDFCreator(String projectName, String outputPDFName) throws IOException, DocumentException {

        LogsSender.send(LogsIntCode.WYGENEROWANO_RAPORT, LogsString.WYGENEROWANO_RAPORT + " " + projectName);

        File outputPDFFile = new File(System.getProperty("user.home") + "\\Desktop\\" + outputPDFName + ".pdf");

        String dir = Project.getDirOfProject(projectName).getPath();
        Chart2d chart2d = new Chart2d();


        Paragraph headLine = createHeadLine();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputPDFFile));
        document.open();


        Files.walk(Paths.get(dir)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                String fileName = filePath.getFileName().toString();
                if (fileName.contains(".mp4")) {
                    String recordName = fileName.replace(".mp4", "");

                    System.out.println(fileName);
                    callInside(dir, chart2d, headLine, document, recordName);
                }
            }
        });


        document.close();
        new FileUtils(outputPDFFile.getAbsolutePath()).openExplorerByPath();
        //System.exit(0);
    }

    public void callInside(String dir, Chart2d chart2d, Paragraph headLine, Document document, String projectName) {
        try {
            inside(dir, projectName, chart2d, headLine, document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void inside(String dir, String projectName, Chart2d chart2d, Paragraph headLine, Document document) throws IOException, DocumentException {
        File gyroData = new File(dir + "\\" + projectName + "-gyroData.txt");
        chart2d.setTrace(gyroData.getPath());
        String chartPath = chart2d.saveImage(dir + "\\" + projectName);

        String recordInfo = dir + "\\" + projectName + "-info.txt";
        String projectInfo = dir + "\\" + "info.txt";

        RenderInfoFile recordInfoRender = new RenderInfoFile(recordInfo);
        RenderInfoFile projectInfoRender = new RenderInfoFile(projectInfo);
        JSONObject projectJSON = projectInfoRender.getJSON();
        JSONObject recordJSON = recordInfoRender.getJSON();

        String zleceniodawca = (String) projectJSON.get("zleceniodawcaProjektu");
        String nazwaProjektu = (String) projectJSON.get("nazwaProjektu");
        String osobaOdpowiedzialna = (String) projectJSON.get("wykonawcaProjektu");
        String rodzajInspekcji = (String) recordJSON.get("inspekcja");
        String rozmiarRury = (String) recordJSON.get("rozmiarRury");
        String material = (String) recordJSON.get("material");
        String dlugoscOdcinka = String.valueOf(chart2d.getLength());
        String numerOdcinka = (String) recordJSON.get("numerOdcinka");
        String ulicaPoczatkowa = (String) recordJSON.get("poczatkowaUlica");
        String studniaPoczatkowa = (String) recordJSON.get("poczatkowaSymbolStudni");
        String ulicaKoncowa = (String) recordJSON.get("koncowaUlica");
        String studniaKoncowa = (String) recordJSON.get("koncowaSymbolStudni");
        String miasto = (String) recordJSON.get("misto");
        String rozmiar = (String) recordJSON.get("rozmiarRury");
        String typInstalacji = (String) recordJSON.get("typInstalacji");
        String przekroj = (String) recordJSON.get("przekroj");
        String kierunekJazdy = (String) recordJSON.get("kierunekJazdy");
        String dlugoscRury = "0m";

        String poczatkowaRzednaKinety = (String) recordJSON.get("poczatkowaRzednaKinety");
        String koncoaRzednaKinety = (String) recordJSON.get("koncowaRzednaKinety");
        BasicFileAttributes attributes = Files.readAttributes(gyroData.toPath(), BasicFileAttributes.class);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String data = df.format(attributes.creationTime().toMillis());

        String[][] tabelaZPierwszejStrony = {
                {"Zleceniodawca", zleceniodawca},
                {"Numer projektu", nazwaProjektu},
                {" ", " "},
                {"Data rozpoczęcia", data},
                {"Data zakończenia", data},
        };

        PdfPTable tablePierwszaStrona = getTableData(2, tabelaZPierwszejStrony);
        if (calledInsideCount == 0) {
            addFirstPage(headLine, document, tablePierwszaStrona);
        } else {
            document.newPage();
        }

        String[][] content =
                {
                        {"Operator", osobaOdpowiedzialna, "Numer odcinka", numerOdcinka},
                        {"Rodzaj inspekcji", rodzajInspekcji, "Ulica początkowa", ulicaPoczatkowa},
                        {"Rozmiar rury", rozmiarRury, "Studnia początkowa", studniaPoczatkowa},
                        {"Materiał", material, "Studnia końcowa", studniaKoncowa},
                        {"Numer mapy", "", "miasto", miasto},
                        {"Długośc odcinka", dlugoscOdcinka, "Data wykonania", data}
                };

        String[][] content2 = {
                {"Studnia początkowa", ""},
                {"Szczegóły studni", ""},
                {"Symbol studni", studniaPoczatkowa},
                {"Typ studni", "Początkowa"},
                {"Rzędna kinety", poczatkowaRzednaKinety},
                {"Rozmiar", rozmiar},
                {"Zagłębienie", "0mm"},
                {"Uwagi", ""},
        };

        String[][] content3 = {
                {"Studnia końcowa", ""},
                {"Symbol studni", studniaKoncowa},
                {"Typ studni", "Początkowa"},
                {"Rzędna kinety", koncoaRzednaKinety},
                {"Rozmiar", rozmiar},
                {"Zagłębienie", "0mm"},
                {"Uwagi", ""},
        };

        String[][] content4 = {
                {"Opis kanału", ""},
                {"Typ instalacji", typInstalacji},
                {"Materiał", material},
                {"Przekroj", przekroj},
                {"Rozmiar", rozmiar},
                {"Kierunek jazdy", kierunekJazdy},
                {"Nr odcinka", numerOdcinka},
                {"Długośc odcinka", dlugoscOdcinka},
                {"Długośc rury", dlugoscRury},
                {"Uwagi", ""},
        };
        PdfPTable table = getTableData(4, content);
        PdfPTable tableData2 = getTableData(2, content2);
        PdfPTable tableData3 = getTableData(2, content3);
        PdfPTable tableData4 = getTableData(2, content4);

        document.add(headLine);
        document.add(table);
        Image image2 = Image.getInstance(chartPath);
        image2.setRotationDegrees(90);
        document.add(image2);
        document.newPage();
        document.add(headLine);
        document.add(table);
        document.add(new Paragraph("Szczegóły studni", title));
        document.add(tableData2);
        document.add(tableData3);
        document.add(new Paragraph("Szczegóły kanału", title));
        document.add(tableData4);
        calledInsideCount++;
    }

    public void addFirstPage(Paragraph headLine, Document document, PdfPTable tablePierwszaStrona) throws DocumentException {
        document.add(headLine);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        Paragraph paragraphTitle = new Paragraph("Dokumentacja z inspekcji TVC", title);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        paragraphTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphTitle);
        document.add(tablePierwszaStrona);
        document.newPage();
    }

    private Paragraph createHeadLine() {
        Paragraph headLine = getCenterParagraph("Leonardo Camerras", Element.ALIGN_CENTER, normal);
        headLine.add(getCenterParagraph("Twój partner w inspekcji", Element.ALIGN_CENTER, normal));
        headLine.add(getCenterParagraph("Berlin / Londyn / New York City", Element.ALIGN_CENTER, normal));
        headLine.add(Chunk.NEWLINE);
        headLine.setAlignment(Element.ALIGN_CENTER);
        ;
        return headLine;
    }

    private Paragraph getCenterParagraph(String text, int align, Font f) {
        Paragraph paragraph = new Paragraph(text, f);
        paragraph.setAlignment(align);
        return paragraph;
    }

    private PdfPTable getTableData(int columns, String[][] content2) {
        PdfPTable tableData = new PdfPTable(columns);
        for (int aw = 0; aw < content2.length; aw++) {
            for (int j = 0; j < content2[aw].length; j++) {
                String text = content2[aw][j];
                if (text != null)
                    if (text.equals("")) text = "---";

                PdfPCell pdfPCell = new PdfPCell(new Phrase(text, normal));
                if ((j + 1) % 2 != 0) {
                    new PdfPCell(new Phrase(text, bold));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

                }
                pdfPCell.setBorder(Rectangle.NO_BORDER);
                tableData.addCell(pdfPCell);
            }
        }
        tableData.setSpacingBefore(10f);
        return tableData;
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    new PDFCreator("Projekt testowy", "Projekt testowy");
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (DocumentException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
    }


}
