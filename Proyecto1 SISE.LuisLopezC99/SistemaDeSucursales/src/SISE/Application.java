package SISE;

public class Application {
    public static void main(String[] args) throws Exception {

        SISE.Presentation.MainView.Model modelPrincipal=new  SISE.Presentation.MainView.Model() ;
        SISE.Presentation.MainView.View viewPrincipal = new  SISE.Presentation.MainView.View();
        SISE.Presentation.MainView.Control controllerPrincipal = new  SISE.Presentation.MainView.Control(modelPrincipal,viewPrincipal);
        PRINCIPAL = controllerPrincipal;
    }
    public static SISE.Presentation.MainView.Control PRINCIPAL;
}