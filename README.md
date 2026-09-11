# Kako zagnati program
Predpogoj sta instalirana JDK in maven.
Zagon programa:
```bash
mvn compile exec:java -Dexec.args="{station_id} {num_buses_per_line} {absolute|relative}"
```

Zagon testov:
```bash
mvn clean test
```

# Kako narediti nalogo zahtevnejso
- Avtobusi, ki vozijo cez polnoc (23:30 -> 25:30)
- Linije, ki bi vozile le ob dolocenih dnevih
- Upostevanje smeri voznje (go ali return)
- Izracun, kdaj bo avtobus na kateri postaji (ob kateri uri)
- Iskanje poti od postaje X do postaje Y (s prestopi)
