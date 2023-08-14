package stepDefenitions;
import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeDeletePlaceApi() throws IOException {
        Steps steps = new Steps();
        if (Steps.place_id == null) {
            steps.add_place_api_payload_with("Ram", "Banglore", "Tamil");
            steps.user_calls_the_api_with_http_request("AddPlaceAPI", "POST");
            steps.api_call_got_success_with_status_code(200);
            steps.verify_place_id_created_maps_to_the_using("Ram", "GetPlaceAPI");
        }
    }


}
