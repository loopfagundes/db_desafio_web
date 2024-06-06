package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CadastraPrimeiraContaPageObject;
import org.openqa.selenium.WebDriver;

public class CadastraPrimeiraContaStep {
    private final CadastraPrimeiraContaPageObject cadastraPrimeiraContaPageObject;

    public CadastraPrimeiraContaStep(WebDriver driver) {
        cadastraPrimeiraContaPageObject = new CadastraPrimeiraContaPageObject(driver);
    }

    public CadastraPrimeiraContaStep indexPage(String email, String password) {
        cadastrarNovaContaDoUsuario(email, password);
        return this;
    }

    private CadastraPrimeiraContaStep cadastrarNovaContaDoUsuario(String email, String password) {
        cadastraPrimeiraContaPageObject.registrarButton().click();
        cadastraPrimeiraContaPageObject.emailTextField().setText(email);
        return this;
    }
}