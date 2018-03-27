package com.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.adapter.NewsAdapter;
import com.dentist.NewsRealActivity;
import com.dentist.R;
import com.entity.NetEnv;
import com.entity.NewsEntity;
import com.example.customswiperefreshlayout.view.CircleImageView;
import com.example.customswiperefreshlayout.view.SunlandProgressDrawable;
import com.example.customswiperefreshlayout.view.SunlandSwipeRefreshLayout;
import com.google.gson.Gson;
import com.interfaces.ICustumClickListener;
import com.interfaces.INetCallback;
import com.network.NetRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment implements  INetCallback, ICustumClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SunlandSwipeRefreshLayout refreshLayout;
    private String url;
    private List<NewsEntity> newsLists;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter newsAdapter;
    private int lastPosition;
    private CircleImageView circleImageView;

    private int num=0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;

    private View view;

    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_news, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        this.refresh();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void refresh(){
        init();
        initData();
        handleScroll();
    }

    private void init() {
        //     progressBar=(ProgressBar)findViewById(R.id.progressBar);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.sv_main);
        refreshLayout = (SunlandSwipeRefreshLayout) view.findViewById(R.id.news_layout);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL));

        refreshLayout.setOnRefreshListener(new SunlandSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetRequest request = new NetRequest(getContext(), url, num+5, (INetCallback)NewsFragment.this);
                num+=5;
                request.doLoadData();
            }
        });
        url = NetEnv.NewsList_SERVER;

    }

    private void initData() {
        newsLists = new ArrayList<NewsEntity>();
        newsAdapter = new NewsAdapter(getContext(), newsLists);
        newsAdapter.setOnClickListener(this);
        recyclerView.setAdapter(newsAdapter);
        initToil();
        NetRequest request = new NetRequest(getContext(), url, num+5, (INetCallback) this);
        num+=5;
        request.doLoadData();
    }

    private void initToil() {
        LinearLayout toilView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.toil_view, refreshLayout, false);
        circleImageView = new CircleImageView(getContext(), CIRCLE_BG_LIGHT, CIRCLE_DIAMETER / 2);
        SunlandProgressDrawable progressDrawable = new SunlandProgressDrawable(getContext(), circleImageView);
        circleImageView.setImageDrawable(progressDrawable);
        toilView.addView(circleImageView);
        newsAdapter.setToilView(toilView);
    }

    @Override
    public void custumClick(View view, int position) {
        if(position==newsLists.size()){
            NetRequest request = new NetRequest(getContext(), url, num+5, (INetCallback) NewsFragment.this);
            num+=5;
            request.doLoadData();
        }
        else{
            Intent intent=new Intent(getActivity(), NewsRealActivity.class);
            intent.putExtra("title",newsLists.get(position).getTitle());
            intent.putExtra("content",newsLists.get(position).getDescription());
            startActivity(intent);
        }
    }

    @Override
    public void netCall(JSONObject jsonObject) {


        if(getActivity()==null)
            return;
        try {
            String temp = jsonObject.getString("newslist");
            JSONArray jsonArray = new JSONArray(temp);
            for (int i = 0; i < jsonArray.length(); i++) {
                Gson gson = new Gson();
                NewsEntity newsEntity = gson.fromJson(jsonArray.get(i).toString(), NewsEntity.class);
                newsLists.add(newsEntity);
            }


            if(getActivity()!=null){
                refreshLayout.setRefreshing(false);
            }
//
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fail() {

    }

    private void handleScroll() {
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (lastPosition == recyclerView.getAdapter().getItemCount() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    refreshLayout.setRefreshing(true);
                    NetRequest request = new NetRequest(getContext(), url, num+5, (INetCallback) NewsFragment.this);
                    num+=5;
                    request.doLoadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastPosition = mLayoutManager.findLastVisibleItemPosition();
            }
        });

    }


}
