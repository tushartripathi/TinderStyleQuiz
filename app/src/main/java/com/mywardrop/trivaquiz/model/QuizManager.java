package com.mywardrop.trivaquiz.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mywardrop.trivaquiz.VolleySingleton;
import com.mywardrop.trivaquiz.controller.CardAdapter;
import com.mywardrop.trivaquiz.controller.QuestionsInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {

    final static String TAG = "app"; //for logcat
    final private RequestQueue mRequestQueue; //to start volley
    private String url; // url for json data
    private Context mContext;
    private QuestionsInterface mQuestionCallback; //interface to update value to activity

    //Constructor
    public QuizManager(Context mContext)
    {
        this.mContext = mContext;
        url = "https://opentdb.com/api.php?amount=10&category=18&difficulty=easy&type=boolean";
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue(); // setting requestQueue
        mQuestionCallback =(QuestionsInterface)mContext; // setting Interface

    }

    //method called from MainActivity
    public void getQuizQuestions(final CardAdapter cardAdapter) //CardAdapter used for notifying
    {

        final ProgressDialog progressDialog = new ProgressDialog(mContext); //progress bar creating
        progressDialog.setMessage("Loading..."); //setting message to progress dialog
        progressDialog.show(); // Display it
        // Json Result when called
        JsonObjectRequest quizJSONObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Getting json array from results keyword
                    JSONArray jsonArray = response.getJSONArray("results");
                    for(int i = 0 ; i< jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);//JSONObject from json array
                        //setting value to QuizQuesiton class objext
                        QuizQuestion q = new QuizQuestion(obj.getString("question"), obj.getBoolean("correct_answer"));
                        mQuestionCallback.getQuestion(q); // transfering value to MainActivity using interface callback
                    }
                  }
                catch(Exception e) {
                    Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                }
                finally {
                    progressDialog.dismiss(); // progress dialogue dismiss
                }
                cardAdapter.notifyDataSetChanged(); // notify data change to Adapter will update the view automatcally
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                }
        );
        mRequestQueue.add(quizJSONObjRequest); //Calling json request
    }
}
