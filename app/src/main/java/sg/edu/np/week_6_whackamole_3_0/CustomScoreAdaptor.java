package sg.edu.np.week_6_whackamole_3_0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CustomScoreAdaptor extends RecyclerView.Adapter<CustomScoreViewHolder> {
    /* Hint:
        1. This is the custom adaptor for the recyclerView list @ levels selection page
     */
    UserData list_members;
    ArrayList<Integer> score_list;
    ArrayList<Integer> level_list;
    Context cContext;
    private static final String FILENAME = "CustomScoreAdaptor.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    public CustomScoreAdaptor(UserData list_members, Context context){
        /* Hint:
        This method takes in the data and readies it for processing.
         */
        list_members = list_members;
        score_list = list_members.getScores();
        level_list = list_members.getLevels();
        cContext = context;
    }

    public CustomScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        /* Hint:
        This method dictates how the viewholder layout is to be once the viewholder is created.
         */
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main3,parent,false);
        return new CustomScoreViewHolder(item);
    }

    public void onBindViewHolder(CustomScoreViewHolder holder, final int position){

        /* Hint:
        This method passes the data to the viewholder upon bounded to the viewholder.
        It may also be used to do an onclick listener here to activate upon user level selections.

        Log.v(TAG, FILENAME + " Showing level " + level_list.get(position) + " with highest score: " + score_list.get(position));
        Log.v(TAG, FILENAME+ ": Load level " + position +" for: " + list_members.getMyUserName());
         */
        holder.hiScore.setText("Highest Score: " + score_list.get(position).toString());
        holder.level.setText("Level " + level_list.get(position).toString());

        Log.v(TAG, FILENAME + " Showing level " + level_list.get(position) + " with highest score: " + score_list.get(position));
        Log.v(TAG, FILENAME+ ": Load level " + position +" for: " + list_members.getMyUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cContext,Main4Activity.class);
                i.putExtra("level",level_list.get(position));
                i.putExtra("currentUser",list_members.getMyUserName());
                cContext.startActivity(i);
            }
        });

    }

    public int getItemCount(){
        /* Hint:
        This method returns the the size of the overall data.
         */
        return score_list.size();
    }
}