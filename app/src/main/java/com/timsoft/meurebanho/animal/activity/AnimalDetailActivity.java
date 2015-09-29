package com.timsoft.meurebanho.animal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.timsoft.meurebanho.MainActivity;
import com.timsoft.meurebanho.MeuRebanhoApp;
import com.timsoft.meurebanho.R;
import com.timsoft.meurebanho.animal.db.DBAnimalAdapter;
import com.timsoft.meurebanho.animal.model.Animal;
import com.timsoft.meurebanho.death.activity.DeathMaintainActivity;
import com.timsoft.meurebanho.event.model.Event;
import com.timsoft.meurebanho.race.db.DBRaceAdapter;
import com.timsoft.meurebanho.race.model.Race;
import com.timsoft.meurebanho.sale.activity.SaleDetailActivity;
import com.timsoft.meurebanho.sale.activity.SaleMaintainActivity;
import com.timsoft.meurebanho.treatment.activity.TreatmentDetailActivity;
import com.timsoft.meurebanho.treatment.activity.TreatmentMaintainActivity;
import com.timsoft.meurebanho.treatment.db.DBTreatmentAdapter;

import java.util.Date;

public class AnimalDetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AnimalDetailActivity";
    private Animal animal;
    private int animalId;
    DBAnimalAdapter animalDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate");

        setContentView(R.layout.animal_detail_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        animalId = getIntent().getExtras().getInt(DBAnimalAdapter.ID);

        final Button btn_add_treatment = (Button) findViewById(R.id.ad_add_treatment);
        btn_add_treatment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AnimalDetailActivity.this, TreatmentMaintainActivity.class);
                intent.putExtra(MeuRebanhoApp.ACTION, MeuRebanhoApp.ACTION_ADD);
                intent.putExtra(DBAnimalAdapter.ID, animal.getId());
                startActivity(intent);
            }
        });

        final Button btn_register_sale = (Button) findViewById(R.id.ad_register_sale);
        btn_register_sale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AnimalDetailActivity.this, SaleMaintainActivity.class);
                intent.putExtra(MeuRebanhoApp.ACTION, MeuRebanhoApp.ACTION_ADD);
                intent.putExtra(DBAnimalAdapter.ID, animal.getId());
                startActivity(intent);
            }
        });

        final Button btn_register_death = (Button) findViewById(R.id.ad_register_death);
        btn_register_death.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AnimalDetailActivity.this, DeathMaintainActivity.class);
                intent.putExtra(MeuRebanhoApp.ACTION, MeuRebanhoApp.ACTION_ADD);
                intent.putExtra(DBAnimalAdapter.ID, animal.getId());
                startActivity(intent);
            }
        });

        final Button btn_register_retire = (Button) findViewById(R.id.ad_register_retire);
        btn_register_retire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(AnimalDetailActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.confirm_delete)
                    .setMessage(R.string.death_confirm_delete)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            animalDatasource.open();
                            animal.setRetireDate(new Date());
                            animalDatasource.update(animal);
                            animalDatasource.close();
                            updateAnimalData();
                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
            }
        });
    }

    private void updateAnimalData() {
        //Animal data
        animalDatasource= DBAnimalAdapter.getInstance();
        animalDatasource.open();
        animal = animalDatasource.get(animalId);
        animalDatasource.close();
        //

        //Race data
        DBRaceAdapter raceDatasource = DBRaceAdapter.getInstance();
        raceDatasource.open();
        Race race = raceDatasource.get(animal.getRaceId());
        raceDatasource.close();
        //

        ((TextView) findViewById(R.id.ad_id))
                .setText(animal.getIdToDisplay());

        ((TextView) findViewById(R.id.ad_name))
                .setText(animal.getName());

        ((TextView) findViewById(R.id.ad_name))
                .setText(animal.getName());

        ((TextView) findViewById(R.id.ad_ear_tag))
                .setText(animal.getEarTag());

        if (animal.getPictureFile().exists()) {
            ((ImageView) findViewById(R.id.ad_picture)).setImageBitmap(BitmapFactory.decodeFile(animal.getPictureFile().getPath()));
        } else {
            ((ImageView) findViewById(R.id.ad_picture)).setImageResource(R.drawable.cow);
        }

        ((TextView) findViewById(R.id.ad_race))
                .setText(race.getDescription());

        ((TextView) findViewById(R.id.ad_sex))
                .setText(animal.getSexToDisplay());

        ((TextView) findViewById(R.id.ad_age))
                .setText(animal.getAgeInMonthsToDisplay());

        TableLayout table = (TableLayout) findViewById(R.id.ad_events_table);
        table.removeAllViews();

        for (final Event e : animal.getEvents()) {
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.event_row, null);
            ((TextView) row.findViewById(R.id.er_icon)).setText(e.getType().getIcon());
            ((TextView) row.findViewById(R.id.er_date)).setText(MainActivity.getFormatedDate(e.getDate()));
            ((TextView) row.findViewById(R.id.er_description)).setText(e.getDescription());
            ((TextView) row.findViewById(R.id.er_plus)).setText(e.getType().isSelectable() ? "+" : "");

            if(e.getType().isSelectable()) {
                row.setOnClickListener(new View.OnClickListener() {
                    private Intent intent;

                    @Override
                    public void onClick(View v) {
                        switch (e.getType()) {
                            case TREATMENT:
                                intent = new Intent(AnimalDetailActivity.this, TreatmentDetailActivity.class);
                                intent.putExtra(MeuRebanhoApp.ACTION, MeuRebanhoApp.ACTION_EDIT);
                                intent.putExtra(DBTreatmentAdapter.ID, e.getEntityId());
                                startActivity(intent);
                                break;

                            case DEATH:
                                Toast.makeText(AnimalDetailActivity.this, "A Implementar...", Toast.LENGTH_SHORT).show();
                                break;

                            case SALE:
                                intent = new Intent(AnimalDetailActivity.this, SaleDetailActivity.class);
                                intent.putExtra(MeuRebanhoApp.ACTION, MeuRebanhoApp.ACTION_EDIT);
                                intent.putExtra(DBAnimalAdapter.ID, e.getEntityId());
                                startActivity(intent);
                                break;

                            default:
                                Toast.makeText(AnimalDetailActivity.this, "No action defined for this event type: " + e.getType().toString(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }

            table.addView(row);
        }
        table.requestLayout();     // Not sure if this is needed.

        Button btn_register_sale = (Button) findViewById(R.id.ad_register_sale);
        if(animal.isSold()) {
            btn_register_sale.setVisibility(View.VISIBLE);
        } else {
            btn_register_sale.setVisibility(View.GONE);
        }

        Button btn_register_death = (Button) findViewById(R.id.ad_register_death);
        if(animal.isDead()) {
            btn_register_death.setVisibility(View.VISIBLE);
        } else {
            btn_register_death.setVisibility(View.GONE);
        }

        Button btn_register_retire = (Button) findViewById(R.id.ad_register_retire);
        Button btn_delete_retire = (Button) findViewById(R.id.ad_delete_retire);
        if(animal.isRetired()) {
            btn_register_retire.setVisibility(View.GONE);
            btn_delete_retire.setVisibility(View.VISIBLE);
        } else {
            btn_register_retire.setVisibility(View.VISIBLE);
            btn_delete_retire.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        updateAnimalData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.animal_detail_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                actionEditAnimal();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void actionEditAnimal() {
        Intent intent = new Intent(this, AnimalMaintainActivity.class);

        intent.putExtra(MeuRebanhoApp.ACTION, MeuRebanhoApp.ACTION_EDIT);
        intent.putExtra(DBAnimalAdapter.ID, animal.getId());

        startActivity(intent);
    }
}