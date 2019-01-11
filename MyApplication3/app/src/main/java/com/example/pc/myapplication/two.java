package com.example.pc.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class two extends Fragment {


    RecyclerView rv;
    private LinearLayoutManager linearLayoutManager;
    Button write_fab;
    android.support.v7.widget.SearchView mSearch;
    private DatabaseReference mUserDatabase;
    static FirebaseRecyclerAdapter adapter;


    public two() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);
        rv = view.findViewById(R.id.recy);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);


        write_fab = view.findViewById(R.id.fab_check1);
        write_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Post_Activity.class);
                startActivity(intent);
            }
        });
        Log.d("fragment_two", "2");
        mUserDatabase = FirebaseDatabase.getInstance().getReference("posts");
        mSearch = (android.support.v7.widget.SearchView) view.findViewById(R.id.mSearch);
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String input) {

                String searchText = input;

                Log.d("fragment_two", "222");
                Query query = mUserDatabase.orderByChild("title").startAt(searchText).endAt(searchText + "\uf8ff");
                Log.d("fragment_two", "222" + query);

                fetch(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        Log.d("fragment_two", "1");
        Query query = mUserDatabase;
        fetch(query);




        return view;

    }


    private void fetch(Query query) {

        Log.d("fragment_two", "10" + query);
        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(query, Post.class)
                        .setLifecycleOwner(this)
                        .build();

        Log.d("fragment_two", "11" + options);

        adapter = new FirebaseRecyclerAdapter<Post, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);
                Log.d("fragment_two", "19");

                ViewHolder holder = new ViewHolder(view);
                return holder;


            }


            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, final Post post) {
                holder.setTxtTitle(post.getTitle());
                holder.setTxtContent(post.getContent());
                Log.d("fragment_two", "14" + holder);
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), Check_Post.class);

                        String title=post.getTitle();
                        String content=post.getContent();
                        intent.putExtra("title", title);
                        intent.putExtra("content",content);
                        Log.d("111",""+title);
                        startActivity(intent);
                    }
                });


            }

        };


        rv.setAdapter(adapter);

        Log.d("fragment_two", "199");

    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        Log.d("fragment_two", "30");
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        Log.d("fragment_two", "31");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtTitle;
        public TextView txtContent;
        View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            root = itemView.findViewById(R.id.list_root);
            txtTitle = itemView.findViewById(R.id.list_title);
            txtContent = itemView.findViewById(R.id.list_content);
            Log.d("fragment_two", "5" + mView);
        }

        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }


        public void setTxtContent(String string) {
            txtContent.setText(string);
        }


    }


    }
