Universitatea Babeș Bolyai, Cluj-Napoca
Informatică în germană


DOCUMENTAȚIE
Calculator de Polinoame








STUDENT: TĂMAȘ ANDREEA-MARIA






1.	Obiectivul aplicației	

Proiectarea și implementarea unui calculator de polinoame cu interfață grafică specifică, în urmă căreia un utilizator se poate înregistra sau loga în aplicație. Mai apoi, acesta poate să introducă 2 polinoame, respectiv rezultatul acestora, și să selecteze operația matematică efectuată, apasand ulterior butonul de validare a răspunsului introdus. Se afișează un mesaj pentru rezultat corect sau greșit. De asemenea, pentru fiecare validare corectă a calculului de polinoame, utlizatorul primește un anumit număr de tokens și un anumit ranking, în funcție de operația matematică efectuată. Prin apăsarea unui buton, acesta poate să își vadă numărul de tokens, rankingul personal, dar poate să creeze și un quest. Acesta din urmă presupune adăugarea unui scurt enunț de teorie despre polinoame, în momentul în care user-ul are cel puțin 15 tokens acumulați. 

    2. Analiza problemelor, modelare, scenarii, cazuri de utilizare

În matematică, un polinom este o expresie construită dintr-una sau mai multe variabile și constante, folosind doar operații de adunare, scădere, înmulțire și ridicare la putere cu exponent întreg pozitiv. Când vine vorba de polinoame intr-o singura variabilă, reprezentată de obicei prin x, ele pot fi întotdeauna scrise sub forma unei sume de monomii, cum ar fi:
∑(𝑎𝑖𝑥𝑖 ) = 𝑎0 + 𝑎1𝑥 + 𝑎2𝑥2 + ⋯ + 𝑎𝑛𝑥𝑛
	
, unde 𝑎0, 𝑎1, 𝑎2, …, 𝑎𝑛 sunt constante. Termenul „nedeterminat” înseamnă că x nu are o valoare specifică, dar orice valoare îi poate fi înlocuită. Fiecare monom constă dintr-un coeficient, un nedeterminat și un grad, care este puterea la care este ridicat nedeterminatul.
O reprezentare alternativă pentru polinoame este o succesiune de perechi ordonate, în care fiecare pereche ordonată reprezintă un termen din polinom. Perechea ordonată este formată din coeficientul și exponentul monomului corespunzător. De exemplu, polinomul 5𝑥2 + 2𝑥 + 1 poate fi reprezentat prin succesiunea {(5,2), (2,1), (1,0)}. Gradul unui polinom este cel mai înalt grad al oricăruia dintre termenii săi și determină intervalul lui i în monomii 𝑎𝑖𝑥𝑖, care variază de la 0 la gradul polinomului.
Folosind această reprezentare a polinoamelor, putem efectua operații comune precum adunarea, scăderea, înmulțirea, împărțirea, diferențierea și integrarea.
Interfața calculatorului permite utilizatorului să introducă două polinoame pentru a efectua diverse operații. Polinoamele introduse sunt apoi stocate sub formă de „polinoame obișnuite”. Utilizatorul poate selecta apoi o anumită operație de efectuat, cum ar fi adunarea, scăderea, înmulțirea, împărțirea, diferențierea sau integrarea. Rezultatul operației selectate este afișat în interfață.

    3. Design

a)	Considerații teoretice

O expresie polinomială este o expresie care poate fi construită din constante și simboluri (numite variabile sau nedeterminate) prin adăugare, înmulțire și exponențiere la o putere nenegativă. Un polinom dintr-un singur x nedeterminat poate fi întotdeauna scris (sau rescris) sub forma:
a n x n + a n-1 x n-1 + a n-2 x n-2 + …+ a 2 x 2 + a 1 x + a 0
,unde a0 , … an sunt constante are se numesc coeficienți ai polinomului, iar x este nedeterminat. Lumea „nedeterminată” înseamnă că x nu reprezintă o valoare anume, deși orice valoare o poate înlocui. 

*ARITMETIC: 
Polinoamele se pot adăuga sau scădea folosind legea asociativă a adunării, grupând toți termenii lor într-o singură sumă, urmată eventual de reordonare, folosind legea comutativă și combinarea termenilor cu aceeași putere. Când se adună sau se scad polinoamele împreună, rezultatul este un alt polinom.
Polinoamele pot fi, de asemenea, multiplicate. Pentru a extinde produsul a două polinoame într-o sumă de termeni, se aplică în mod repetat legea distributivă, ceea ce are ca rezultat înmulțirea fiecărui termen al unui polinom cu fiecare termen al celuilalt.
Calcularea diferenţialelor şi integralelor polinoamelor este deosebit de simplă, în comparaţie cu alte tipuri de funcţii. Diferenţialul polinomului


în raport cu x este polinomul: 
                                     

                          b)Package diagram
În dezvoltarea de programare orientată pe obiecte (OOP), model-view-controller (MVC) este numele unei metodologii pentru a lega cu succes și eficient interfața cu utilizatorul la modelele de date subiacente. Aceasta propune trei domenii principale care vor fi utilizate în dezvoltarea aplicației:
- Model: reprezintă structura logică a datelor utilizate de aplicația software;
- View: reprezintă o colecție de elemente utilizate în interfața cu utilizatorul (tot ceea ce utilizatorul poate vedea și cu care poate interacționa; de exemplu butoane, casete de text, etichete, scroller etc.);
- Controller: reprezintă legătura dintre model și vedere.

Acest calculator de polinoame are următoarele pachete:

-GUI (Graphical User Interface) : contine 2 clase, View si Controller
-Polynomial : contine 2 clase, Polinom si Monom
-Operation : contine 1 clasa, Operations, ce implementeaza adunarea, scaderea, inmultirea, diferentierea si integrarea datelor de intrare
-Results: contine 1 clasa, Result ce retine catul si restul a doua polinoame 
 
c)	Swing UI Designer
De asemenea, aplicatia are 2 Swing-uri UI Designer :
-Login: contine clasa Login (Dialog Class) si formul Login.form (GUI Form)
-Register: contine clasa Register (Dialog Class)  si formul Register.form (GUI Form)
 

Am folosit si o baza de date in care se retin datele despre utilizatorii ce au deja un cont in aplicatie.
