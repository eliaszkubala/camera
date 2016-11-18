package kamera.event.request;

import kamera.event.ui.EventTree;

/**
 * Created by TheKing on 17.11.2016.
 */
public class CodesRequest {
    private static EventTree mOthers;
    private static EventTree mStocktaking;
    private static EventTree mPipeline;
    private static EventTree mManhole;

    public static EventTree getmOthers() {
        return mOthers;
    }

    public static EventTree getmStocktaking() {
        return mStocktaking;
    }

    public static EventTree getmPipeline() {
        return mPipeline;
    }

    public static EventTree getmManhole() {
        return mManhole;
    }

    static {
        mPipeline = new EventTree();
        mPipeline.addOld("BAA - Deformacja", "A - pionowa - wysokość przewodu została zredukowana", new String[]{});
        mPipeline.addOld("BAA - Deformacja", "B - pozioma - szerokość przewodu została zredukowana", new String[]{});

        mPipeline.addOld("BAB - Szczelina", "A - zarysowanie", new String[]{"A - wzdłużna", "B - obwodowe", "C - złożone", "D - spiralne"});
        mPipeline.addOld("BAB - Szczelina", "B - pęknięcie", new String[]{"A - wzdłużna", "B - obwodowe", "C - złożone", "D - spiralne"});
        mPipeline.addOld("BAB - Szczelina", "C - złamanie", new String[]{"A - wzdłużna", "B - obwodowe", "C - złożone", "D - spiralne"});

        mPipeline.addOld("BAC - Przerwanie/Zapadnięcie", "A - przerwanie", new String[]{});
        mPipeline.addOld("BAC - Przerwanie/Zapadnięcie", "B - brakujące", new String[]{});
        mPipeline.addOld("BAC - Przerwanie/Zapadnięcie", "C - zapadnięcie się", new String[]{});

        mPipeline.addOld("BAD - Wadliwe roboty murarskie lub kamieniarskie", "A - przemieszczone", new String[]{"A - kolejna warstwa ułożonych cegieł lub kamiena jest widoczna", "B - nic nie jest widoczne"});
        mPipeline.addOld("BAD - Wadliwe roboty murarskie lub kamieniarskie", "B - brakujące", new String[]{"A - kolejna warstwa ułożonych cegieł lub kamiena jest widoczna", "B - nic nie jest widoczne"});
        mPipeline.addOld("BAD - Wadliwe roboty murarskie lub kamieniarskie", "D - zapadnięcie się", new String[]{"A - kolejna warstwa ułożonych cegieł lub kamiena jest widoczna", "B - nic nie jest widoczne"});

        mPipeline.addOld("BAE - Brakująca zaprawa", null, null);

        String[] BAFpod = new String[]{"A - działanie mechaniczne", "B - działanie chemiczne - ogólnie", "C - działanie chemiczne - zniszczenie powyżej poziomu ścieków", "D - działanie chemiczne - zniszczenie poniżej poziomu ścieków", "E - przyczyna nie jest jednoznaczna"};
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "A - zwiększona chropowatość", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "B - wykruszanie się", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "C - widoczne kruszywo", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "D - kruszywo wystające z powierchni", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "E - brakujące kruszywo", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "F - widoczne zbrojenie", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "G - zbrojenie wystające z powierchni", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "H - skorodowane zbrojenie", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "I - brakująca ściana", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "J - produkty korozji na powierzchni", BAFpod);
        mPipeline.addOld("BAF - Uszkodzenie powierzchniowe", "Z - inne uszkodzenie powierzchniowe", BAFpod);

        mPipeline.addOld("BAG - Połączenie wystające", null, null);

        mPipeline.add("BAH - Wadliwe połączenie", "A - umiejscowienie połączenia jest nieprawidłowe");
        mPipeline.add("BAH - Wadliwe połączenie", "B - obecna jest przerwa");
        mPipeline.add("BAH - Wadliwe połączenie", "C - obecna jest częściowa przerwa");
        mPipeline.add("BAH - Wadliwe połączenie", "D - przewód przyłączeniowy jest uszkodzony");
        mPipeline.add("BAH - Wadliwe połączenie", "E - przewód przyłączeniowy jest zablokowany");
        mPipeline.add("BAH - Wadliwe połączenie", "Z - inne");

        mPipeline.addOld("BAI - Wystające uszczelnienie", "A - pierścień uszczelniający", new String[]{"A - widoczne przemieszczona, ale nie wystająca", "B - wisząca, ale nie wystająca", "C - wisząca, ale nie złamana", "D - złamana"});
        mPipeline.addOld("BAI - Wystające uszczelnienie", "Z - inne materiały uszczelniające", new String[]{"A - widoczne przemieszczona, ale nie wystająca", "B - wisząca, ale nie wystająca", "C - wisząca, ale nie złamana", "D - złamana"});

        mPipeline.add("BAJ - Przemieszczone złącze", "A - wzdłużne");
        mPipeline.add("BAJ - Przemieszczone złącze", "B - promieniste");
        mPipeline.add("BAJ - Przemieszczone złącze", "C - pod kątem");

        mPipeline.addOld("BAK - Wada wykładziny", "A - wykładzina jest oderwana", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        mPipeline.addOld("BAK - Wada wykładziny", "B - zmiana barwy wykładziny", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        mPipeline.addOld("BAK - Wada wykładziny", "C - wadliwa końcówka wykładziny", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        mPipeline.addOld("BAK - Wada wykładziny", "D - pomarszczona wykładzina", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        mPipeline.addOld("BAK - Wada wykładziny", "E - wykładzina z pęcherzami", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});
        mPipeline.addOld("BAK - Wada wykładziny", "Z - inne wady wykładziny", new String[]{"A - wzdłużny", "B - obwodowy", "C - złożony"});

        mPipeline.add("BAL - Wadliwa naprawa", "A - brak części ściany");
        mPipeline.add("BAL - Wadliwa naprawa", "B - łata wypełniająca otwór celowo wykonana w ścianie przewodu");
        mPipeline.add("BAL - Wadliwa naprawa", "Z - inne");

        mPipeline.add("BAM - Uszkodzenie spoiny", "A - wzdłużne");
        mPipeline.add("BAM - Uszkodzenie spoiny", "B - obwodowe");
        mPipeline.add("BAM - Uszkodzenie spoiny", "C - spiralne");

        mPipeline.add("BAN - Przewód porowaty");

        mPipeline.add("BAO - Grunt widoczny przez wadę");

        mPipeline.add("BAP - Pusta przestrzeń widoczna przez wadę");

        mPipeline.add("BBA - Korzenie", "A - korzeń zbity");
        mPipeline.add("BBA - Korzenie", "B - niezależne drobne korzenie");
        mPipeline.add("BBA - Korzenie", "C - złożona masa korzeni");

        mPipeline.add("BBB - Przyczepione osady", "A - inkrustracja");
        mPipeline.add("BBB - Przyczepione osady", "B - tłuszcz");
        mPipeline.add("BBB - Przyczepione osady", "C - śluzowaty osad");
        mPipeline.add("BBB - Przyczepione osady", "Z - inne");

        mPipeline.add("BBC - Odłożone osady", "A - drobnoziarnisty");
        mPipeline.add("BBC - Odłożone osady", "B - gruboziarnisty");
        mPipeline.add("BBC - Odłożone osady", "C - twardy lub zbity materiał");
        mPipeline.add("BBC - Odłożone osady", "Z - inne");

        mPipeline.add("BBD - Wpadanie gruntu", "A - piach");
        mPipeline.add("BBD - Wpadanie gruntu", "B - torf");
        mPipeline.add("BBD - Wpadanie gruntu", "C - materiał drobnoziarnisty");
        mPipeline.add("BBD - Wpadanie gruntu", "D - żwir");
        mPipeline.add("BBD - Wpadanie gruntu", "Z - inne");

        mPipeline.add("BBE - Inne przeszkody", "A - zerwana cegła lub element kamieniarski leżący na dnie");
        mPipeline.add("BBE - Inne przeszkody", "B - torf");
        mPipeline.add("BBE - Inne przeszkody", "C - inny obiekt leżący na dnie");
        mPipeline.add("BBE - Inne przeszkody", "D - wystający ze ściany");
        mPipeline.add("BBE - Inne przeszkody", "E - zaklinowany w złączu");
        mPipeline.add("BBE - Inne przeszkody", "F - wchodząca przez połączenie/przewód łącznikowy");
        mPipeline.add("BBE - Inne przeszkody", "G - zewnętrzne przewody lub kable przechodzące przez rurociąg");
        mPipeline.add("BBE - Inne przeszkody", "H - wbudowane w strukturę");
        mPipeline.add("BBE - Inne przeszkody", "Z - inne");

        mPipeline.add("BBF - Infiltracja", "A - pocenie");
        mPipeline.add("BBF - Infiltracja", "B - kapanie");
        mPipeline.add("BBF - Infiltracja", "C - płynący");
        mPipeline.add("BBF - Infiltracja", "D - tryskający");

        mPipeline.add("BBG - Eksfiltracja");

        mPipeline.add("BBH - Szkodniki i insekty", "A - szczury", "A - w rurociągu", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
        mPipeline.add("BBH - Szkodniki i insekty", "B - karaluchy", "A - w rurociągu", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
        mPipeline.add("BBH - Szkodniki i insekty", "Z - inne", "A - w rurociągu", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");


        mStocktaking = new EventTree();
        mStocktaking.add("BCA - Połączenie", "A - łącznik", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        mStocktaking.add("BCA - Połączenie", "B - połączenie siodłowe - wywiercone", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        mStocktaking.add("BCA - Połączenie", "C - połączenie siodłowe - wydłutowane", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        mStocktaking.add("BCA - Połączenie", "D - proste połączenie - wywiercone", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        mStocktaking.add("BCA - Połączenie", "E - proste połączenie - wydłutowane", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        mStocktaking.add("BCA - Połączenie", "F - połączenie inne niż łącznik", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        mStocktaking.add("BCA - Połączenie", "G - rodzaj połączenia nieokreślony", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");
        mStocktaking.add("BCA - Połączenie", "Z - inny rodzaj połączenia", "A - przyłącze otwarte", "B - przyłącze zamknięte", "C - w otwartym złączu");

        mStocktaking.add("BCB - Naprawa punktowa", "A - wymieniony przewód");
        mStocktaking.add("BCB - Naprawa punktowa", "B - miejscowa wykładzina");
        mStocktaking.add("BCB - Naprawa punktowa", "C - wstrzyknięta zaprawa cementowa");
        mStocktaking.add("BCB - Naprawa punktowa", "D - inne wstrzyknięte materiały uszczelniające");
        mStocktaking.add("BCB - Naprawa punktowa", "E - załatany otwór");
        mStocktaking.add("BCB - Naprawa punktowa", "Z - inne bezwykopowe metody naprawy");


        mStocktaking.add("BCC - Krzywizna kanału", "A - lewy", "A - góra", "B - dół");
        mStocktaking.add("BCC - Krzywizna kanału", "B - prawy", "A - góra", "B - dół");

        mStocktaking.add("BCD - Węzeł początkowy", "A - studzienka włazowa");
        mStocktaking.add("BCD - Węzeł początkowy", "B - studzienka niewłazowa");
        mStocktaking.add("BCD - Węzeł początkowy", "C - podłączenie do kanałów ulicznych");
        mStocktaking.add("BCD - Węzeł początkowy", "D - otwór na lampę");
        mStocktaking.add("BCD - Węzeł początkowy", "E - wylot");
        mStocktaking.add("BCD - Węzeł początkowy", "F - ważne połączenia bez studzienki włazowej lub studzienki niewłazowej");
        mStocktaking.add("BCD - Węzeł początkowy", "XA - rodzaj zdefiniowany przez władze eksploatacyjne");
        mStocktaking.add("BCD - Węzeł początkowy", "Z - inne specjalne studzienki");


        mStocktaking.add("BCE - Węzeł końcowy", "A - studzienka włazowa");
        mStocktaking.add("BCE - Węzeł końcowy", "B - studzienka niewłazowa");
        mStocktaking.add("BCE - Węzeł końcowy", "C - podłączenie do kanałów ulicznych");
        mStocktaking.add("BCE - Węzeł końcowy", "D - otwór na lampę");
        mStocktaking.add("BCE - Węzeł końcowy", "E - wylot");
        mStocktaking.add("BCE - Węzeł końcowy", "F - ważne połączenia bez studzienki włazowej lub studzienki niewłazowej");
        mStocktaking.add("BCE - Węzeł końcowy", "XA - rodzaj zdefiniowany przez władze eksploatacyjne");
        mStocktaking.add("BCE - Węzeł końcowy", "Z - inne specjalne studzienki");

        mStocktaking.add("DCA - Rodzaj połączenia", "A - połączenie w kinecie");
        mStocktaking.add("DCA - Rodzaj połączenia", "B - swobodny spadek do kinety");
        mStocktaking.add("DCA - Rodzaj połączenia", "C - kaskadowe");
        mStocktaking.add("DCA - Rodzaj połączenia", "D - z rurą wewnętrzną");
        mStocktaking.add("DCA - Rodzaj połączenia", "E - połączenie wyniesione nad kinetę");
        mStocktaking.add("DCA - Rodzaj połączenia", "F - przewód wentylacyjny");
        mStocktaking.add("DCA - Rodzaj połączenia", "Z - inne");

        mStocktaking.add("DCB - Naprawa punktowa", "A - wymieniono część ścianki");
        mStocktaking.add("DCB - Naprawa punktowa", "B - miejscowa wykładzina");
        mStocktaking.add("DCB - Naprawa punktowa", "C - wstrzyknięty materiał uszczelniający");
        mStocktaking.add("DCB - Naprawa punktowa", "D - inne");

        mStocktaking.add("DCG - Połączeniowy (boczny) rurociąg", "A - kołowy", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        mStocktaking.add("DCG - Połączeniowy (boczny) rurociąg", "B - prostokątny", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        mStocktaking.add("DCG - Połączeniowy (boczny) rurociąg", "C - jajowy", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        mStocktaking.add("DCG - Połączeniowy (boczny) rurociąg", "D - w kształcie litery U", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        mStocktaking.add("DCG - Połączeniowy (boczny) rurociąg", "E - łukowaty", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        mStocktaking.add("DCG - Połączeniowy (boczny) rurociąg", "F - owalny", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        mStocktaking.add("DCG - Połączeniowy (boczny) rurociąg", "X - lokalny odcinek zdefiniowany przez władze eksploatującą", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");
        mStocktaking.add("DCG - Połączeniowy (boczny) rurociąg", "Z - inne", "A - połączenie prowadzące do studzienki włazowej lub studzienki niewłazowej", "B - połączenie prowadzące ze studzienki włazowej lub studzienki niewłazowej", "C - połączenie zamknięte");

        mStocktaking.add("DCH - Spocznik", "A - spocznik uszkodzony");
        mStocktaking.add("DCH - Spocznik", "B - spocznik nieuszkodzony");

        mStocktaking.add("DCI - Kineta", "A - kineta uszkodzona");
        mStocktaking.add("DCI - Kineta", "B - kineta nieuszkodzona");

        mStocktaking.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "A - obecny jest łańcuch bezpieczeństwa bez uszkodzeń");
        mStocktaking.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "B - brakuje łańcucha bezpieczeństwa");
        mStocktaking.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "C - uszkodzony łańcuch bezpieczeństwa");
        mStocktaking.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "D - łańcuch bezpieczeństwa znajduje się na swoim położeniu, ale jest pokryty zanieczyszczeniami");
        mStocktaking.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "E - uchwyty obecne bez uszkodzeń");
        mStocktaking.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "F - brakuje uchwytów bezpieczeństwa");
        mStocktaking.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "G - uszkodzone uchwyty bezpieczeństwa");
        mStocktaking.add("DCJ - Łańcuchy bezpieczeństwa/uchwyty", "H - uchwyty bezpieczeństwa znajdują się na swoim położeniu, ale są pokryte zanieczyszczeniami");

        mStocktaking.add("DCK - Kontrola przepływu", "A - przelew", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "B - syfon", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "C - kryza pomiarowa", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "D - przepływomierz typu vortex", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "E - zasuwa klapowa", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "F - zasuwa sterowana płynnie", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "G - zwężka pomiarowa", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "H - zawór klapowy", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "I - kraty", "A - przepływ ciągły", "B - przelewy");
        mStocktaking.add("DCK - Kontrola przepływu", "Z - inne", "A - przepływ ciągły", "B - przelewy");

        mStocktaking.add("DCL - Uszczelniona rura przechodząca przez studzienkę", "A - brak dostępu do przewodu", "A - uszkodzony", "B - nieuszkodzony");
        mStocktaking.add("DCL - Uszczelniona rura przechodząca przez studzienkę", "B - dostęp jest zapewniony - pokrywa na miejscu", "A - uszkodzony", "B - nieuszkodzony");
        mStocktaking.add("DCL - Uszczelniona rura przechodząca przez studzienkę", "C - zapewniony dostęp - brak pokrywy", "A - uszkodzony", "B - nieuszkodzony");

        mStocktaking.add("DCM - Kosz osadowy", "A - obecny kosz niewadliwy");
        mStocktaking.add("DCM - Kosz osadowy", "B - brakujący kosz");
        mStocktaking.add("DCM - Kosz osadowy", "C - wadliwy kosz");

        mStocktaking.add("DCN - Osadnik", "A - osadnik niewadliwy");
        mStocktaking.add("DCN - Osadnik", "B - osadnik wadliwy");

        mStocktaking.add("DCO - Przekrój poprzeczny", "A - kołowy");
        mStocktaking.add("DCO - Przekrój poprzeczny", "B - prostokątny");
        mStocktaking.add("DCO - Przekrój poprzeczny", "XA - przekrój lokalny");
        mStocktaking.add("DCO - Przekrój poprzeczny", "Z - inne");


        mOthers = new EventTree();
        mOthers.add("BDA - Zdjęcia ogólne");

        mOthers.add("BDB - Uwaga ogólna");

        mOthers.add("BDC - Przerwana inspekcja", "A - przeszkoda");
        mOthers.add("BDC - Przerwana inspekcja", "B - wysoki poziom ścieków");
        mOthers.add("BDC - Przerwana inspekcja", "C - awaria sprzętu");
        mOthers.add("BDC - Przerwana inspekcja", "Z - inne");

        mOthers.add("BDD - Poziom ścieków", "A - przejrzyste (dno jest widoczne)");
        mOthers.add("BDD - Poziom ścieków", "B - mętne lub barwne");

        mOthers.add("BDE - Przepływ w przewodzie podłączonym", "A - przejrzyste (dno jest widoczne)", "A - źle podłączony, ponieważ ścieki odpływają do przewodu odpływowego wody powierzchniowej lub kanału", "B - źle podłączony, ponieważ obserwowana woda powierzchniowa odpływa do ściekowego przewodu lub kanału", "C - nie zaobserwowano, że źle podłączony");
        mOthers.add("BDE - Przepływ w przewodzie podłączonym", "B - mętne lub barwne", "A - źle podłączony, ponieważ ścieki odpływają do przewodu odpływowego wody powierzchniowej lub kanału", "B - źle podłączony, ponieważ obserwowana woda powierzchniowa odpływa do ściekowego przewodu lub kanału", "C - nie zaobserwowano, że źle podłączony");
        mOthers.add("BDE - Przepływ w przewodzie podłączonym", "YY - poziom ścieków w głównym przewodzie za wysoki", "A - źle podłączony, ponieważ ścieki odpływają do przewodu odpływowego wody powierzchniowej lub kanału", "B - źle podłączony, ponieważ obserwowana woda powierzchniowa odpływa do ściekowego przewodu lub kanału", "C - nie zaobserwowano, że źle podłączony");

        mOthers.add("BDF - Atmosfera wewnątrz rurociągu", "A - niedobór tlenu");
        mOthers.add("BDF - Atmosfera wewnątrz rurociągu", "B - siarkowodór");
        mOthers.add("BDF - Atmosfera wewnątrz rurociągu", "C - metan");
        mOthers.add("BDF - Atmosfera wewnątrz rurociągu", "Z - inne");

        mOthers.add("BDG - Utrata widoczności", "A - kamera jest pod wodą");
        mOthers.add("BDG - Utrata widoczności", "B - szlam");
        mOthers.add("BDG - Utrata widoczności", "C - para");
        mOthers.add("BDG - Utrata widoczności", "Z - inne");

        mOthers.add("DDA - Zdjęcie ogólne");

        mOthers.add("DDB - Uwaga ogólna");

        mOthers.add("DDC - Przerwana inspekcja", "A - niemożność podniesienia pokrywy");
        mOthers.add("DDC - Przerwana inspekcja", "B - zablokowanie");
        mOthers.add("DDC - Przerwana inspekcja", "C - wysoki poziom ścieków");
        mOthers.add("DDC - Przerwana inspekcja", "D - awaria sprzętu");
        mOthers.add("DDC - Przerwana inspekcja", "Z - inne");

        mOthers.add("DDE - Przepływ we wchodzącym przewodzie", "A - przejrzyste ścieki", "A - źle podłączony, ścieki odprowadzane są do przewodów kanalizacji deszczowej lub systemu ściekowego", "B - źle podłączone, wody powierzchniowe odprowadzane są do przewodu ściekowego lub kolektora", "C - nie obserwuje się złego podłączenia");
        mOthers.add("DDE - Przepływ we wchodzącym przewodzie", "B - mętne lub zabarwione ścieki", "A - źle podłączony, ścieki odprowadzane są do przewodów kanalizacji deszczowej lub systemu ściekowego", "B - źle podłączone, wody powierzchniowe odprowadzane są do przewodu ściekowego lub kolektora", "C - nie obserwuje się złego podłączenia");
        mOthers.add("DDE - Przepływ we wchodzącym przewodzie", "YY - przepływ w przewodzie przyłączeniowym jest niewidoczny", "A - źle podłączony, ścieki odprowadzane są do przewodów kanalizacji deszczowej lub systemu ściekowego", "B - źle podłączone, wody powierzchniowe odprowadzane są do przewodu ściekowego lub kolektora", "C - nie obserwuje się złego podłączenia");

        mOthers.add("DDF - Atmosfera w komorze", "A - niedobór tlenu");
        mOthers.add("DDF - Atmosfera w komorze", "B - siarkowodór");
        mOthers.add("DDF - Atmosfera w komorze", "C - metan");
        mOthers.add("DDF - Atmosfera w komorze", "Z - inne");

        mOthers.add("DDG - Utrata widoczności", "A - kamera znajduje się pod ściekami");
        mOthers.add("DDG - Utrata widoczności", "B - szlam");
        mOthers.add("DDG - Utrata widoczności", "C - para");
        mOthers.add("DDG - Utrata widoczności", "Z - inne");


        mManhole = new EventTree();

        mManhole.add("DAA - Deformacja", "A - ogólna");
        mManhole.add("DAA - Deformacja", "B - zlokalizowana");

        mManhole.add("DAB - Szczelina", "A - zarysowanie", "A - pionowa", "B - pozioma", "C - złożona", "D - pochyłe");
        mManhole.add("DAB - Szczelina", "B - pęknięcie", "A - pionowa", "B - pozioma", "C - złożona", "D - pochyłe");
        mManhole.add("DAB - Szczelina", "C - załamanie", "A - pionowa", "B - pozioma", "C - złożona", "D - pochyłe");

        mManhole.add("DAC - Przerwanie/Zapadnięcie się", "A - przerwanie");
        mManhole.add("DAC - Przerwanie/Zapadnięcie się", "B - brakujące");
        mManhole.add("DAC - Przerwanie/Zapadnięcie się", "C - zapadnięcie się");

        mManhole.add("DAD - Wadliwe roboty murarskie lub kamieniarskie",
                "A - przemieszczone",
                "A - kolejna warstwa cegieł albo kamienia jest widoczna", "B - nic nie jest widoczne");

        mManhole.add("DAD - Wadliwe roboty murarskie lub kamieniarskie",
                "B - brakujące",
                "A - kolejna warstwa cegieł albo kamienia jest widoczna", "B - nic nie jest widoczne");

        mManhole.add("DAD - Wadliwe roboty murarskie lub kamieniarskie",
                "C - zapadnięcie się",
                "A - kolejna warstwa cegieł albo kamienia jest widoczna", "B - nic nie jest widoczne");

        mManhole.add("DAE - Brakująca zaprawa");

        mManhole.add("DAF - Uszkodzenie powierzchniowe", "A - zwiększona chropowatość", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "B - wykruszanie się", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "C - widoczne kruszywo", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "D - kruszywo wystające z powierzchni", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "E - brakujące kruszywo", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "F - widoczne zbrojenie", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "G - zbrojenie wystające z powierzchni", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "H - skorodowane zbrojenie", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "I - brakująca ściana", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "J - produkty korozji na powierzchni", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "K - działanie chemiczne - działanie biochemiczne", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "L - działanie chemiczne - działanie ścieków", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "M - przyczyna nie jest jednoznaczna", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");
        mManhole.add("DAF - Uszkodzenie powierzchniowe", "Z - inne uszkodzenia powierzchniowe", "A - działanie mechaniczne", "B - działanie chemiczne - ogólne");

        mManhole.add("DAG - Zakłócające połączenie");

        mManhole.add("DAH - Wadliwe połączenie", "A - umiejscowienie połączenia jest nieprawidłowe");
        mManhole.add("DAH - Wadliwe połączenie", "B - obecna jest przerwa pomiędzy końcem przewodu a ścianą studzienki");
        mManhole.add("DAH - Wadliwe połączenie", "C - obecna jest częściowa przerwa pomiędzy kończem przewodu a ścianą studzienki");
        mManhole.add("DAH - Wadliwe połączenie", "D - przewód przyłączeniowy jest uszkodzony");
        mManhole.add("DAH - Wadliwe połączenie", "E - przewód przyłączeniowy jest zablokowany");
        mManhole.add("DAH - Wadliwe połączenie", "Z - inne");

        mManhole.add("DAI - Wystające uszczelnienie", "A - pierścień uszczelniający", "A - widocznie przemieszczona, ale nie wystająca do komory", "B - wisząca, ale nie złamana");
        mManhole.add("DAI - Wystające uszczelnienie", "Z - inne materiały uszczelniające", "A - widocznie przemieszczona, ale nie wystająca do komory", "B - wisząca, ale nie złamana");
        mManhole.add("DAI - Wystające uszczelnienie", "C - złamana", "A - widocznie przemieszczona, ale nie wystająca do komory", "B - wisząca, ale nie złamana");

        mManhole.add("DAJ - Przemieszczone złącze", "A - pionowe");
        mManhole.add("DAJ - Przemieszczone złącze", "B - poziomo");
        mManhole.add("DAJ - Przemieszczone złącze", "C - pod kątem");


        mManhole.add("DAK - Wada wykładziny", "A - wykładzina jest oderwana", "A - pionowy", "B - poziomy");
        mManhole.add("DAK - Wada wykładziny", "B - zmiana barwy wykładziny", "A - pionowy", "B - poziomy");
        mManhole.add("DAK - Wada wykładziny", "C - wadliwa końcówka wykładziny", "A - pionowy", "B - poziomy");
        mManhole.add("DAK - Wada wykładziny", "D - pomarszczona wykładzina", "A - pionowy", "B - poziomy");
        mManhole.add("DAK - Wada wykładziny", "E - wykładzina z pęcherzami", "A - pionowy", "B - poziomy");
        mManhole.add("DAK - Wada wykładziny", "Z - inne wady wykładziny", "A - pionowy", "B - poziomy");
        mManhole.add("DAK - Wada wykładziny", "C - złożony", "A - pionowy", "B - poziomy");

        mManhole.add("DAL - Wadliwa naprawa", "A - brak części ściany");
        mManhole.add("DAL - Wadliwa naprawa", "B - łata wypełniająca otwór celowo wykonana w ścianie jest teraz wadliwa");
        mManhole.add("DAL - Wadliwa naprawa", "Z - inne");

        mManhole.add("DAM - Uszkodzenie spoiny", "A - poziome");
        mManhole.add("DAM - Uszkodzenie spoiny", "B - pionowe");
        mManhole.add("DAM - Uszkodzenie spoiny", "C - pochyłe");

        mManhole.add("DAN - Ściana porowata");

        mManhole.add("DAO - Grunt widoczny przez wadę");

        mManhole.add("DAP - Pusta przestrzeń widoczna przez wadę");

        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "A - obluzowany stopień włazowy");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "B - brakujący stopień złazowy");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "C - stopień złazowy jest skorodowany");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "D - stopień złazowy jest zgięty");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "E - plastikowa powłoka stopnia włazowego jest połamana");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "F - poręcz drabiny włazowej jest skorodowana");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "G - podpora drabiny włazowej jest obluzowana");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "H - brakująca podpora drabiny włazowej");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "I - podpora drabiny włazowej jest skorodowana");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "J - stopień drabiny skorodowany");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "K - wadliwy otwór na palce stopy");
        mManhole.add("DAQ - Wadliwy stopień włazowy lub drabina włazowa", "Z - inne");

        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "A - złamana pokrywa włazu");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "B - kołysająca się pokrywa włazu");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "C - brakująca pokrywa włazu");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "D - złamana rama");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "E - obluzowana rama");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "F - brakująca rama");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "G - pokrywa włazu poniżej poziomu powierzchni");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "H - pokrywa włazu powyżej poziomu powierzchni");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "I - podpora drabiny włazowej jest skorodowana");
        mManhole.add("DAR - Wadliwa pokrywa włazu lub rama", "Z - inne");

        mManhole.add("DBA - Korzenie", "A - korzeń zbity");
        mManhole.add("DBA - Korzenie", "B - niezależne drobne korzenie");
        mManhole.add("DBA - Korzenie", "C - złożona masa korzeni");

        mManhole.add("DBB - Przyczepione osady", "A - inkrustracja");
        mManhole.add("DBB - Przyczepione osady", "B - tłuszcz");
        mManhole.add("DBB - Przyczepione osady", "C - śluzowaty osad");
        mManhole.add("DBB - Przyczepione osady", "Z - inne");

        mManhole.add("DBC - Odłożone osady", "A - drobnoziarnisty");
        mManhole.add("DBC - Odłożone osady", "B - gruboziarnisty");
        mManhole.add("DBC - Odłożone osady", "C - twardy lub zbity materiał");
        mManhole.add("DBC - Odłożone osady", "Z - inne");

        mManhole.add("DBD - Wpadanie gruntu");

        mManhole.add("DBE - Inne przeszkody", "A - zerwana cegła lub element kamieniarski");
        mManhole.add("DBE - Inne przeszkody", "B - kawałki połamanego przewodu leżące na dnie");
        mManhole.add("DBE - Inne przeszkody", "C - inny obiekt leżący na dnie");
        mManhole.add("DBE - Inne przeszkody", "D - wystający ze ściany");
        mManhole.add("DBE - Inne przeszkody", "E - zaklinowany w złączy");
        mManhole.add("DBE - Inne przeszkody", "F - wychodząca przez połączenie/przewód łącznikowy");
        mManhole.add("DBE - Inne przeszkody", "G - zewnętrzne przewody lub kable przechodzące przez strukturę");
        mManhole.add("DBE - Inne przeszkody", "H - wbudowane w strukturę");
        mManhole.add("DBE - Inne przeszkody", "Z - inne");

        mManhole.add("DBF - Infiltracja", "A - pocenie", "A - przez ścianę studzienki włazowej lub studzienki niewłazowej", "B - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki na poziomie dna", "C - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki ponad spocznikiem");
        mManhole.add("DBF - Infiltracja", "B - kapanie", "A - przez ścianę studzienki włazowej lub studzienki niewłazowej", "B - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki na poziomie dna", "C - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki ponad spocznikiem");
        mManhole.add("DBF - Infiltracja", "C - płynący", "A - przez ścianę studzienki włazowej lub studzienki niewłazowej", "B - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki na poziomie dna", "C - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki ponad spocznikiem");
        mManhole.add("DBF - Infiltracja", "D - tryskający", "A - przez ścianę studzienki włazowej lub studzienki niewłazowej", "B - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki na poziomie dna", "C - przez przestrzeń pomiędzy przewodem połączeniowym a ścianą studzienki ponad spocznikiem");

        mManhole.add("DBG - Eksfiltracja");

        mManhole.add("DBH - Szkodniki i insekty", "A - szczury", "A - w studzience włazowej lub studzience niewłazowej", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
        mManhole.add("DBH - Szkodniki i insekty", "B - karaluchy", "A - w studzience włazowej lub studzience niewłazowej", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
        mManhole.add("DBH - Szkodniki i insekty", "Z - inne", "A - w studzience włazowej lub studzience niewłazowej", "B - w połączeniu", "C - w otwartym złączu", "Z - inne");
    }
}
