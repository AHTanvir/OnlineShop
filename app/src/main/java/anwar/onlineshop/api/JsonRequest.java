package anwar.onlineshop.api;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import anwar.onlineshop.*;

/**
 * A request for retrieving a {@link JSONObject} response body at a given URL, allowing for an
 * optional {@link JSONObject} to be passed in as part of the request body.
 * Usable for authorized and unauthorized requests.
 */
public class JsonRequest extends JsonObjectRequest {

    private int requestStatusCode;
    private String requestUrl;
    public JsonRequest(int method, String requestUrl, JSONObject jsonRequest, Response.Listener<JSONObject> successListener,
                       Response.ErrorListener errorListener) {
        super(method, requestUrl, jsonRequest, successListener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            requestStatusCode = response.statusCode;
            System.out.println("Respons code = "+response.statusCode);
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
        return super.parseNetworkResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null) {
            // Save request status code
            requestStatusCode = volleyError.networkResponse.statusCode;
        } else {
            requestStatusCode = consts.MissingStatusCode;
        }
        return super.parseNetworkError(volleyError);
    }

    /**
     * Method returns result statusCode of invoked request.
     *
     * @return HTTP status code.
     */
    public int getStatusCode() {
        return requestStatusCode;
    }
}
