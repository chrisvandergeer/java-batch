package nl.cge.common;

import javax.inject.Inject;

public class NaamGenerator {

    @Inject
    private Voornamen voornamen;

    @Inject
    private Achternamen achternamen;

    public String genereerNaam() {
        return String.format("%s %s",
                voornamen.getRandomVoornaam(),
                achternamen.getRandomAchternaam());
    }
}
