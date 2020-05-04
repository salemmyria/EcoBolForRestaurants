package fr.eisti.android.ecobolforrestaurants;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class Commandes extends AppCompatActivity {


    ImageView commandes;
    ImageView promos;
    ImageView stock;
    TextView commandesText;
    TextView promosText;
    TextView stockText;


    HashMap<String, List<String>> myCommand;
    List<String> myCommandDetails;
    ExpandableListView expandableListView;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commandes);

        expandableListView= (ExpandableListView)findViewById(R.id.idListView);
        myCommand= DataProvider.getInfo();
        myCommandDetails = new ArrayList<>(myCommand.keySet());

        adapter = new MyAdapter(this,myCommand,myCommandDetails);
        expandableListView.setAdapter(adapter);

        stock = (ImageView)findViewById(R.id.stock);
        stockText= (TextView)findViewById(R.id.stocktext);
        stock.setColorFilter(Color.parseColor("#605E5E"));
        stockText.setTextColor(Color.parseColor("#605E5E"));

        promos = (ImageView)findViewById(R.id.promos);
        promosText = (TextView)findViewById(R.id.promostext);
        promos.setColorFilter(Color.parseColor("#605E5E"));
        promosText.setTextColor(Color.parseColor("#605E5E"));

        commandes = (ImageView)findViewById(R.id.commandes);
        commandesText=(TextView)findViewById(R.id.commandestext);
        commandes.setColorFilter(Color.parseColor("#7CB342"));
        commandesText.setTextColor(Color.parseColor("#7CB342"));

        //commandes.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);

        promos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Commandes.this,Promos.class));

            }
        });

        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Commandes.this,Historique.class));

            }
        });
        Context context;








    }


    class MyAdapter extends BaseExpandableListAdapter {
        private Context ctx;
        private HashMap<String,List<String>> ChildTitles;
        private List<String> HeaderTitles;


        MyAdapter(Context ctx, HashMap<String, List<String>> ChildTitles, List<String> HeaderTitles)
        {
            this.ctx=ctx;
            this.ChildTitles=ChildTitles;
            this.HeaderTitles=HeaderTitles;


        }

        @Override
        public int getGroupCount()
        {
            return HeaderTitles.size();
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return ChildTitles.get(HeaderTitles.get(groupPosition)).size();
        }


        @Override
        public Object getGroup(int groupPosition)
        {
            return HeaderTitles.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return ChildTitles.get(HeaderTitles.get(groupPosition)).get(childPosition);
        }


        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return groupPosition;
        }


        @Override
        public boolean hasStableIds()
        {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {
            String title = (String) this.getGroup(groupPosition);
            if(convertView== null)
            {
                LayoutInflater inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView= inflater.inflate(R.layout.command_header,null);

            }
            TextView txt = (TextView)convertView.findViewById(R.id.idTitle);
            txt.setTypeface(null, Typeface.BOLD);

            txt.setText(title);
            return convertView;
        }




         @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
         {
             String title = (String) this.getChild(groupPosition,childPosition);

             final RecyclerView.ViewHolder holder;
             
            // Button buttonTerminee = (Button) findViewById(R.id.terminee);
             //buttonTerminee.setVisibility(Button.GONE);

             if(convertView== null)
             {
                 LayoutInflater inflater= (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                 convertView = inflater.inflate(R.layout.command_children,null);

             }

             TextView txt = (TextView) convertView.findViewById(R.id.idChild);
             if(title.contains("Burger classique"))
             {
                 txt.setTypeface(null,Typeface.BOLD_ITALIC);
                 txt.setText(title);
             }
             else
                 txt.setText(title);



             View buttonAccepter = convertView.findViewById(R.id.accepter);
             View buttonRefuser = convertView.findViewById(R.id.refuser);
             View buttonTerminee = convertView.findViewById(R.id.terminee);


             if(isLastChild)
             {

                LayoutInflater inflater= (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.command_children,null);
                buttonAccepter.setVisibility(View.VISIBLE);
                buttonAccepter.setFocusable(false);
                buttonRefuser.setFocusable(false);
                buttonTerminee.setFocusable(false);

                buttonRefuser.setVisibility(View.VISIBLE);
                buttonTerminee.setVisibility(View.GONE);
                buttonRefuser.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         //Toast toast;
                         Toast.makeText(ctx,"Test Button",Toast.LENGTH_SHORT).show();
                         //buttonTerminee.setVisibility(View.VISIBLE);
                     }
                 });

                //buttonTerminee.setVisibility(View.GONE);



             }
             else
             {
                 buttonAccepter.setVisibility(View.GONE);
                 buttonRefuser.setVisibility(View.GONE);

                 //buttonTerminee.setVisibility(View.GONE);
                 //buttonTerminee.setVisibility(View.GONE);
             }


             //txt.setText(title);
             AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
             builder.setCancelable(true);
             builder.setTitle("Test Message");
             builder.setMessage("You have a new command");
             builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     startActivity(new Intent(Commandes.this,Promos.class));
                 }
             });
             builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     startActivity(new Intent(Commandes.this,Historique.class));
                 }
             });

             AlertDialog dialog = builder.create();
             dialog.show();

             return convertView;
         }



         @Override
        public boolean isChildSelectable(int groupPosition, int childPosistion)
         {
             return true;
         }

    }



    static class DataProvider {
        public static HashMap<String, List<String>> getInfo()
        {
            HashMap<String, List<String>> HeaderDetails = new HashMap<String, List<String>>();

            List<String> ChildDetails1 = new ArrayList<>();
            ChildDetails1.add("Burger classique                                                                                                                     13,00€");
            ChildDetails1.add("Boeufs");
            ChildDetails1.add("Frites");
            ChildDetails1.add("+ Glace vanille");
            ChildDetails1.add("Burger classique                                                                                                                     12,00€");
            ChildDetails1.add("Poulet");
            ChildDetails1.add("Frites");
            ChildDetails1.add(" ");


            List<String> ChildDetails2 = new ArrayList<>();
            ChildDetails2.add("Burger classique                                                                                                                     13,00€");
            ChildDetails2.add("Boeufs");
            ChildDetails2.add("Frites");
            ChildDetails2.add("+ Glace vanille");
            ChildDetails2.add("Burger classique                                                                                                                     12,00€");
            ChildDetails2.add("Poulet");
            ChildDetails2.add("Frites");
            ChildDetails2.add(" ");

            HeaderDetails.put("Martin (pour 19h30)                                                  25,00€",ChildDetails1);
            HeaderDetails.put("Alex                                                                                   25,00€",ChildDetails2);

            return HeaderDetails;


        }
    }



    public void cancelButton(View v) {
        Button buttonAccepter = (Button)findViewById(R.id.accepter);
        Button buttonRefuser = (Button)findViewById(R.id.refuser);
        buttonAccepter.setVisibility(View.GONE);
        buttonRefuser.setVisibility(View.GONE);
    }



}

