package bugify.appmanager;

public class ApplicationManager {
    private RestHelper restHelper;

    public RestHelper restHelper(){
        if(restHelper == null){
            restHelper = new RestHelper();
        }
        return restHelper;
    }
}
