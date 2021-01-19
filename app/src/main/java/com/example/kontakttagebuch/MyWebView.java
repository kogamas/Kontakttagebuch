package com.example.kontakttagebuch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyWebView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyWebView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WebView webView;

    public MyWebView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebView.
     */
    // TODO: Rename and change types and number of parameters
    public static MyWebView newInstance(String param1, String param2) {
        MyWebView fragment = new MyWebView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        requireActivity().setContentView(R.layout.fragment_web_view);

        webView = (WebView) view.findViewById(R.id.webView1);

        // Enable Javascript
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);

        webView.loadUrl(
                 "https://symptomchecker.fsw.at/");
                //"https://filesamples.com/samples/document/txt/sample1.txt");
        // Inflate the layout for this fragment
        return view;
    }
}