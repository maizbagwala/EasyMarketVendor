package com.dexterapps.easymarketvendor.config;

public class ApiRequest {
//    public static void Call_Api(final Context context, int method, String url, @Nullable JSONObject jsonObject,
//                                     final Callback callback, @Nullable HashMap<String, String> headers) {
//
//        Log.d(Variables.TAG, "Call_Api: " + url);
//        if (jsonObject != null) {
//            Log.d(Variables.TAG, "Call_Api: " + jsonObject.toString());
//        }
//
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, jsonObject, response -> {
//            Log.d(Variables.TAG, "Call_Api: " + response.toString());
//            if (callback != null) {
//                callback.Response(response.toString());
//            }
//
//        }, error -> {
//            Log.d(Variables.TAG, "Call_Api: " + error.toString());
//            if (callback != null) {
//                callback.Response(error.toString());
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                if (headers != null) {
//                    return headers;
//                } else {
//                    return super.getHeaders();
//                }
//            }
//        };
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        requestQueue.add(jsonObjectRequest);
//
//    }






//        JSONObject parameters = new JSONObject();
//        try {
//            parameters.put("username", et_mobile.getText().toString());
//            parameters.put("password", et_password.getText().toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        loader.show();
//
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("Auth", "value");

//        ApiRequest.Call_Api(this, Request.Method.GET, Variables.BANNER_URL, null, response -> {
//        loader.cancel();
//        try {
//                JSONObject jsonObject = new JSONObject(response);
//                if (jsonObject.get("error_id") != null) {
//                    int response_id = (int) jsonObject.get("error_id");
//                    if (response_id == 0) {
//                        startActivity(new Intent(this, MainActivity.class));
//                        finish();
//                    }
//                    Toast.makeText(this, "" + jsonObject.get("error_msg"), Toast.LENGTH_LONG).show();
//                }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }, null);
}
