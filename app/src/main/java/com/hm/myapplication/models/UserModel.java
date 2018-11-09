package com.hm.myapplication.models;

/*

{
  "name": "John",
  "surname": "Doe",
  "gender": "male",
  "region": "United States"
}

 */

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel {

    public String name = "";
    public String surname = "";
    public String gender = "";
    public String region = "";
    public String photoUrl = "";

    public UserModel() {


    }

    public UserModel(JSONObject object) {

        try {

            if (object.has("name") && !object.isNull("name")) {

                this.name = object.getString("name");
            }

            if (object.has("surname") && !object.isNull("surname")) {

                this.surname = object.getString("surname");
            }

            if (object.has("gender") && !object.isNull("gender")) {

                this.gender = object.getString("gender");
            }

            if (object.has("region") && !object.isNull("region")) {

                this.region = object.getString("region");
            }

            if (object.has("photo") && !object.isNull("photo")) {

                this.photoUrl = object.getString("photo");
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }

    }
}