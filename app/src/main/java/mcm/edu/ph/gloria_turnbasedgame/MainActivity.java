package mcm.edu.ph.gloria_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtMonsName,txtMonsHP,txtMonsMP,txtHeroName,txtHeroHP,txtHeroMP,txtHeroDPS,txtMonsDPS,txtLog;
    Button btnNextTurn;
    ImageButton skill1,skill2,skill3,skill4;

    String heroName = "Yae Miko";
    int heroHP = 1500;
    int heroMP = 1000;
    int heroMinDamage = 100;
    int heroMaxDamage = 150;


    String monsName = "Arataki Gang";
    int monsterHP = 3000;
    int monsterMP = 400;
    int monsterMinDamage = 40;
    int monsterMaxDamage = 55;

    int turnNumber = 1;

    boolean disabledStatus = false;
    int statuscounter = 0;
    int buttoncounter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHeroName = findViewById(R.id.txtHeroName);
        txtMonsName = findViewById(R.id.txtMonsName);
        txtHeroHP = findViewById(R.id.txtHeroHP);
        txtMonsHP = findViewById(R.id.txtMonsHP);
        txtHeroMP = findViewById(R.id.txtHeroMP);
        txtMonsMP = findViewById(R.id.txtMonsMP);
        txtHeroDPS = findViewById(R.id.txtHeroDPS);
        txtMonsDPS = findViewById(R.id.txtMonsDPS);

        txtHeroName.setText(heroName);
        txtHeroHP.setText(String.valueOf(heroHP));
        txtHeroMP.setText(String.valueOf(heroMP));

        txtMonsName.setText(monsName);
        txtMonsHP.setText(String.valueOf(monsterHP));
        txtMonsMP.setText(String.valueOf(monsterMP));

        txtHeroDPS.setText(String.valueOf(heroMinDamage)+ " ~ "+ String.valueOf(heroMaxDamage));
        txtMonsDPS.setText(String.valueOf(monsterMinDamage)+ " ~ "+ String.valueOf(monsterMaxDamage));

        skill1 = findViewById(R.id.btnSkill1);
        skill2 = findViewById(R.id.btnSkill2);
        skill3 = findViewById(R.id.btnSkill3);
        skill4 = findViewById(R.id.btnSkill4);

        txtLog = findViewById(R.id.txtCombatLog);

        btnNextTurn.setOnClickListener(this);
        skill1.setOnClickListener(this);
    }

    @Override
    protected void onClick(View v) {



        txtLog = findViewById(R.id.txtCombatLog);

        Random randomizer = new Random();
        int herodps = randomizer.nextInt((heroMaxDamage - heroMinDamage) + heroMinDamage;
        int monsdps = randomizer.nextInt((monsterMaxDamage - monsterMinDamage) + monsterMinDamage;

        if(turnNumber % 2 != 1){
            skill1.setEnabled(false);
        }
        else if (turnNumber % 2 == 1){
            skill1.setEnabled(true);
        }


        if(buttoncounter>0){
            skill1.setEnabled(false);
            buttoncounter--;
        }
        else if(buttoncounter==0){
            skill1.setEnabled(true);
        }


        switch(v.getId()) {
            case R.id.btnSkill1:

                monsterHP = monsterHP - 250;
                turnNumber++;
                txtMonsHP.setText(String.valueOf(monsterHP));
                btnNextTurn.setText("Next Turn("+ String.valueOf(turnNumber)+")");

                disabledStatus = true;
                statuscounter = 4;

                txtLog.setText("Our Hero"+ String.valueOf(heroName)+ " used stun! It dealt"+String.valueOf(250)+"to the enemy. The enemy is stunned for 4 turns");

                if(monsterHP < 0) {
                    txtLog.setText("Our Hero" + String.valueOf(heroName) + "dealt" + String.valueOf(herodps) + "to the enemy. The player is victorious!");
                    heroHP = 1500;
                    monsterHP = 3000;
                    turnNumber = 1;
                    btnNextTurn.setText("Reset Game");
                }
                buttoncounter=12;

                break;

            case R.id.btnNextTurn:

                if(turnNumber % 2 ==1){
                    monsterHP = monsterHP - herodps;
                    turnNumber++;
                    txtMonsHP.setText(String.valueOf(monsterHP));
                    btnNextTurn.setText("Next Turn("+ String.valueOf(turnNumber)+")");

                    txtLog.setText("Our Hero"+ String.valueOf(heroName)+ "dealt"+String.valueOf(herodps)+"to the enemy");

                    if(monsterHP < 0){
                        txtLog.setText("Our Hero"+ String.valueOf(heroName)+ "dealt"+String.valueOf(herodps)+"to the enemy. The player is victorious!");
                        heroHP = 1500;
                        monsterHP = 3000;
                        turnNumber = 1;
                        btnNextTurn.setText("Reset Game");

                    }

                    if(statuscounter>0){
                        statuscounter--;
                        if(statuscounter==0){
                            disabledStatus=false;
                        }

                    }
                    buttoncounter--;

                }
                else if(turnNumber % 2 !=1){


                    if(disabledStatus==true){
                        txtLog.setText("The enemy is still stunned for"+String.valueOf(statuscounter)+ "turns");
                        statuscounter--;
                        if(statuscounter==0){
                            disabledStatus=false;
                        }

                    }
                    else{
                        heroHP = heroHP - monsdps;
                        turnNumber++;
                        txtHeroHP.setText(String.valueOf(heroHP));
                        btnNextTurn.setText("Next Turn("+ String.valueOf(turnNumber)+")");

                        txtLog.setText("The Monster"+ String.valueOf(monsName) + "dealt"+String.valueOf(monsdps) +"damage to the enemy");

                        if(heroHP < 0) {
                            txtLog.setText("The Monster" + String.valueOf(monsName) + "dealt" + String.valueOf(monsdps) + "damage to the enemy. Game Over!");
                            heroHP = 1500;
                            monsterHP = 3000;
                            turnNumber = 1;
                            btnNextTurn.setText("Reset Game");
                        }
                    }
                    buttoncounter--;
                }
                break;
        }
    }
}