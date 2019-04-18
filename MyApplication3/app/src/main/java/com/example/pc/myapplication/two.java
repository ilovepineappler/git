package com.example.pc.myapplication;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.media.CamcorderProfile.get;


/**
 * A simple {@link Fragment} subclass.
 */
public class two extends Fragment implements View.OnClickListener {


    RecyclerView rv;
    private LinearLayoutManager linearLayoutManager;
    android.support.v7.widget.SearchView mSearch;
    static FirebaseRecyclerAdapter adapter;
    View view;

    Query query;
    Query query2;
    Query query3;

    int hint;
    int main;
    int left;
    int size;
    int newsize;
    int i;
    int j = 0;

    List<Long> list;
    List<Long> listcallback2;
    static List<Long> listcallback3;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    DatabaseReference databaseReference3;
    DatabaseReference databaseReference4;

    public boolean one = true;
    public boolean two;
    public boolean three = false;
    public boolean four;
    public boolean five;
    public  boolean six;

    Button right_btn;
    Button left_btn;
    Button write_fab;
    Button[] mybtn;


    public two() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_two, container, false);
        rv = view.findViewById(R.id.recy);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("posts");
        query2 = databaseReference.orderByChild("date2").limitToFirst(3);
        fetch(query2);
        //one=false;

        makeboardnum(new MyCallback() {
            @Override
            public void onCallback(int size) {

                newsize = size;
                clicknumbutton();
            }
        });

        Button refresh = view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(two.this).attach(two.this).commit();

                clicknumbutton();

                one = true;
                five=true;
                j=0;
            }
        });


        write_fab = view.findViewById(R.id.fab_check1);
        write_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Post_Activity.class);
                startActivity(intent);



                clicknumbutton();

                one = true;
                five = true;
                j = 0;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(two.this).attach(two.this).commit();


            }

        });

        mSearch = view.findViewById(R.id.mSearch);
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String input) {

                String searchText = input;
                Log.d("ppp", "" + searchText);
                databaseReference2 = FirebaseDatabase.getInstance().getReference().child("posts");
                query = databaseReference2.orderByChild("title").startAt(searchText)
                        .endAt(searchText + "\uf8ff");
                fetch(query);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        right_btn = view.findViewById(R.id.rignt_btn);
        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three = true;
                j = j + 3;
                clicknumbutton();

            }
        });


        left_btn = view.findViewById(R.id.left_btn);
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three = true;
                j = j - 3;
                clicknumbutton();

            }
        });




        return view;
    }





    public interface MyCallback {
        void onCallback(int size);

    }


    public void makeboardnum( final MyCallback myCallback) {


        FirebaseDatabase.getInstance().getReference().child("posts")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        size = (int) dataSnapshot.getChildrenCount();

                        myCallback.onCallback(size);


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });


    }


    public void clicknumbutton() {

        main = size / 3;
        left = size % 3;

        mybtn = new Button[100];
        if (left == 0) {

            if (one == true) {
                Log.d("pppp", "1");

                if (five == true) {
                    LinearLayout lenear = view.findViewById(R.id.lenear);
                    lenear.removeAllViews();
                    five = false;
                    Log.d("pppp", "2");
                }

                for (i = 0; i < 3; i++) {


                    mybtn[i] = new Button(getActivity());
                    LinearLayout lenear = view.findViewById(R.id.lenear);
                    mybtn[i].setText("" + (i + 1));
                    mybtn[i].setTextSize(10);
                    mybtn[i].setId(i);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 1, 1, 1);
                    mybtn[i].setLayoutParams(layoutParams);
                    lenear.addView(mybtn[i]);
                    mybtn[i].setOnClickListener(this);
                    Log.d("fragment2", "" + mybtn[i].getId());
                    Log.d("pppp", "3");
                }
                one = false;
            }
//            else if (two == true) {
//                if (left != 0) {
//                    mybtn[main] = new Button(getActivity());
//                    LinearLayout lenear = view.findViewById(R.id.lenear);
//                    mybtn[main].setText("" + (main + 1));
//                    mybtn[main].setTextSize(10);
//                    mybtn[main].setId(main);
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    layoutParams.setMargins(10, 1, 1, 1);
//                    mybtn[main].setLayoutParams(layoutParams);
//                    lenear.addView(mybtn[main]);
//                    mybtn[main].setOnClickListener(this);
//
//                    Log.d("fragment4", "" + one);
//
//                }
//
//                two = false;
//            }


            else if (three == true) {

                LinearLayout lenear = view.findViewById(R.id.lenear);
                lenear.removeAllViews();
                query2 = databaseReference.orderByChild("date2").startAt(listcallback3.get(j * 3)).endAt(listcallback3.get((j * 3) + 2));
                fetch(query2);
                int k;
                k = j + 3;

                for (j = j + 0; j < k; j++) {
                    Log.d("fragment5", "" + j);
                    Log.d("fragment5", "" + k);
                    mybtn[j] = new Button(getActivity());
                    lenear = view.findViewById(R.id.lenear);
                    mybtn[j].setText("" + (j + 1));
                    mybtn[j].setTextSize(10);
                    mybtn[j].setId(j);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 1, 1, 1);
                    mybtn[j].setLayoutParams(layoutParams);
                    lenear.addView(mybtn[j]);
                    mybtn[j].setOnClickListener(this);
                    Log.d("fragment5", "" + j);
                    Log.d("fragment5", "" + k);
                    three = false;
                }
                j = j - 3;
                Log.d("fragment5", "" + j);

            } else if (four == true) {

                LinearLayout lenear = view.findViewById(R.id.lenear);
                lenear.removeAllViews();
                Log.d("fragment4", "" + main);
                query3 = databaseReference.orderByChild("date2").startAt(listcallback3.get(j * 3)).endAt(listcallback3.get(j * 3) + 2);
                Log.d("fragment5", "" + listcallback3.get(3));
                fetch(query3);
                int k;
                k = j + 3;
                for (j = j + 0; j < k; j++) {
                    Log.d("fragment5", "" + j);
                    Log.d("fragment5", "" + k);
                    mybtn[j] = new Button(getActivity());
                    lenear = view.findViewById(R.id.lenear);
                    mybtn[j].setText("" + (j + 1));
                    mybtn[j].setTextSize(10);
                    mybtn[j].setId(j);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 1, 1, 1);
                    mybtn[j].setLayoutParams(layoutParams);
                    lenear.addView(mybtn[j]);
                    mybtn[j].setOnClickListener(this);
                    Log.d("fragment5", "" + j);
                    Log.d("fragment5", "" + k);
                    three = false;
                }
                j = j - 3;

                Log.d("fragment5", "" + j);
            }
        } else {

            if (one == true) {
                Log.d("ppppl", "1");

                if (five == true) {
                    LinearLayout lenear = view.findViewById(R.id.lenear);
                    lenear.removeAllViews();
                    five = false;
                    Log.d("ppppl", "2");
                }

                for (i = 0; i < 3; i++) {


                    mybtn[i] = new Button(getActivity());
                    LinearLayout lenear = view.findViewById(R.id.lenear);
                    mybtn[i].setText("" + (i + 1));
                    mybtn[i].setTextSize(10);
                    mybtn[i].setId(i);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 1, 1, 1);
                    mybtn[i].setLayoutParams(layoutParams);
                    lenear.addView(mybtn[i]);
                    mybtn[i].setOnClickListener(this);
                    Log.d("fragment2", "" + mybtn[i].getId());
                    Log.d("ppppl", "3");
                }
                one = false;
            }

//                else if(two==true){
//                if(left==1) {
//                    mybtn[main + left] = new Button(getActivity());
//                    LinearLayout lenear = view.findViewById(R.id.lenear);
//                    mybtn[main + left].setText("" + (main + left));
//                    mybtn[main + left].setTextSize(10);
//                    mybtn[main + left].setId(main);
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    layoutParams.setMargins(10, 1, 1, 1);
//                    mybtn[main + left].setLayoutParams(layoutParams);
//                    lenear.addView(mybtn[main + left]);
//                    mybtn[main + left].setOnClickListener(this);
//                    Log.d("fragment6", "" + one);
//
//                }
//
//                two = false;
//            }
            else if (three == true) {

                LinearLayout lenear = view.findViewById(R.id.lenear);
                lenear.removeAllViews();
                Log.d("fragment4", "" + main);
                query2 = databaseReference.orderByChild("date2").startAt(listcallback3.get(j * 3)).endAt(listcallback3.get((j * 3) + 2));
                Log.d("fragment5", "" + listcallback3.get(3));
                fetch(query2);
//                    Log.d("fragment5", "" + listcallback3.get(3));
                int k = 0;
                k = j + 3;
                for (j = j + 0; j < k; j++) {
                    Log.d("fragment5", "" + j);
                    Log.d("fragment5", "" + k);
                    mybtn[j] = new Button(getActivity());
                    lenear = view.findViewById(R.id.lenear);
                    mybtn[j].setText("" + (j + 1));
                    mybtn[j].setTextSize(10);
                    mybtn[j].setId(j);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 1, 1, 1);
                    mybtn[j].setLayoutParams(layoutParams);
                    lenear.addView(mybtn[j]);
                    mybtn[j].setOnClickListener(this);
                    Log.d("fragment5", "" + j);
                    Log.d("fragment5", "" + k);
                    three = false;
                }
                j = j - 3;
                Log.d("fragment5", "" + j);

            } else if (four == true) {

                LinearLayout lenear = view.findViewById(R.id.lenear);
                lenear.removeAllViews();
                Log.d("fragment4", "" + main);
                query3 = databaseReference.orderByChild("date2").startAt(listcallback3.get(j * 3)).endAt(listcallback3.get((j * 3) + 2));
                Log.d("fragment5", "" + listcallback3.get(3));
                fetch(query3);
                int k;
                k = j + 3;
                for (j = j + 0; j < k; j++) {
                    Log.d("fragment5", "" + j);
                    Log.d("fragment5", "" + k);
                    mybtn[j] = new Button(getActivity());
                    lenear = view.findViewById(R.id.lenear);
                    mybtn[j].setText("" + (j + 1));
                    mybtn[j].setTextSize(10);
                    mybtn[j].setId(j);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 1, 1, 1);
                    mybtn[j].setLayoutParams(layoutParams);
                    lenear.addView(mybtn[j]);
                    mybtn[j].setOnClickListener(this);
                    Log.d("fragment5", "" + j);
                    Log.d("fragment5", "" + k);
                    three = false;
                }
                j = j - 3;

                Log.d("fragment5", "" + j);

            }


        }

    }











    public void onClick(View v) {

        hint = v.getId();
        Log.d("bbb", "" +  hint);
        awesomeButtonClicked(new MyCallback2() {
            @Override
            public void onCallback(List<Long> listcallback) {

                listcallback2=listcallback;
                if(listcallback2!=null && listcallback2.size()==newsize ){

                    listcallback3=listcallback2;

                    savelist(listcallback3);

                }

            }


        });


    }

    public void savelist(List<Long> list2)
    {
        listcallback3=list2;

    }
    public interface MyCallback2{
        void onCallback( List<Long> listcallback);

    }

    private void awesomeButtonClicked(final MyCallback2 myCallback) {

      //  if (left == 0) {
            if(listcallback3!=null) {

                int start = hint*3;
                int end = (hint * 3)+2;

                Log.d("list",""+listcallback3);
                Long numstart = listcallback3.get(start);
                Long numend = listcallback3.get(end);

                Log.d("list",""+numstart);
                Log.d("list",""+numend );


                query2= databaseReference.orderByChild("date2").startAt(numstart).endAt( numend);
                fetch(query2);


           }
            databaseReference.orderByChild("date2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<Long> list = new ArrayList<>();
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        Long eventID = ds.child("date2").getValue(Long.class);
                     list.add(eventID);
                     myCallback.onCallback(list);

                         }

                 }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

//        else {
//
//            if(listcallback3!=null) {
//                Log.d("fragmen6", "" + listcallback3);
//                int start =  hint*3;
//                int end = (hint * 3)+2;
//
//                Long numstart = listcallback3.get(start);
//                Long numend = listcallback3.get(end);
//
//                Log.d("fragmen6", "" +  numstart);
//                Log.d("fragmen6", "" +  numend);
//                query2= databaseReference.orderByChild("date2").startAt(numstart).endAt( numend);
//
//                fetch(query2);
//
//            }
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    list = new ArrayList<>();
//                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                        Long eventID = ds.child("date2").getValue(Long.class);
//
//                        list.add(eventID);
//                        myCallback.onCallback(list);
//                    }
//
//                }
//                @Override
//                public void onCancelled(DatabaseError error) {
//
//                }
//            });


 //       }
 //   }




                    private void fetch (Query query){


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
                                ViewHolder holder = new ViewHolder(view);
                                Log.d("fragment_two", "19");

                                return holder;


                            }


                            @Override
                            protected void onBindViewHolder(ViewHolder holder, final int position, final Post post) {

                                holder.setTxtTitle(post.getTitle());
                                holder.setTxtContent(post.getContent());


                                holder.root.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), Check_Post.class);
                                        String title = post.getTitle();
                                        String content = post.getContent();
                                        intent.putExtra("title", title);
                                        intent.putExtra("content", content);
                                        Log.d("111", "" + title);
                                        startActivity(intent);


                                    }
                                });


                            }

                        };


                        rv.setAdapter(adapter);

                        Log.d("fragment_two", "199");

                    }


                    @Override
                    public void onStart () {
                        super.onStart();
                        adapter.startListening();
                        Log.d("fragment_two", "30");


                    }

                    @Override
                    public void onStop () {
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

                        }

                        public void setTxtTitle(String string) {
                            txtTitle.setText(string);
                        }


                        public void setTxtContent(String string) {
                            txtContent.setText(string);
                        }


                    }



            }

