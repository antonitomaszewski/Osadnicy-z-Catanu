# Osadnicy-z-Catanu
Projekt Programowanie Obiektowe 

W obecnej wersji działa tworzenie, losowanie i malowanie mapy.
Malowanie budynków.
Klasa Gracz.


Czego brakuje:
karty postępu, porty, jeśli gra bez pustyni będzie gorsza może będzie trzeba ją dodać,
Ewentualne rozszerzenie na 5-6 graczy--> trzeba będzie dodać dodatkowe wektory w mapie, zmiany w losowaniu, ale pozatym to nie powinien być problem.

Cały panel wymiany gracz-bank. Gracz-Gracz będzie znacznie bardziej skomplikowany.
Wymiana Gracz-Gracz musiałaby wyglądać jakoś tak, że obok boolów budowy, byłby bool wymiana.
Gdy go wybierzesz pokazuje się większy panel, na którym widzisz tylko swoje surowce. Wymiana Gracz-Bank byłaby po prostu taka,
że klikasz wymień z bankiem i wybierasz jaki surowiec chcesz, wtedy odpala się funkcja, która liczy możliwe wymiany na ten surowiec biorąc pod uwagę zajęte porty. 4-1 (4 te same na 1 dowolny), 3-1 ( z portem 3 te same na 1 dowolny),
2-1 (z portem 2 te same na 1 tego typu co jest port).
+ Losowanie portów nie byłoby takie jak w oryginale, że są sztywne punkty. Wyglądałoby raczej tak, że mamy listę liczb portów,
idziemy po wierzchokach, mających 2 sasiadów, sprawdzamy czy nie ma już tu portu  i losujemy boola(t/f), możliwe że (0 1 2, żeby nie powtarzało się na początku za często) czy tu chcemy postawić i jeśli chcemy, to losujemy z pozostałych portów jeden, usuwamy go z tamtej listy i ustawiamy ten port na ten wierzchołek + jeden z sąsiadów. (możnaby dopuścić, aby jeden punkt miał dostęp do dwóch portów, co byłoby ciekawe i chyba tak chciałbym to napisać, w takim układzie nie możnaby jednak pozwolić raczej, by w jednym punkcie były dwa takie same porty).
Każdy Gracz trzymałby w sobie listę portów (3-1, 2-1 owca, 2-1 siano, 2-1 drewno, 2-1 cegła, 2-1 kamień) byłyby to boole.
