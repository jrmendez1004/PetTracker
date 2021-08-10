package com.example.pettracker.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pettracker.Adapters.CalendarAdapter;
import com.example.pettracker.Adapters.DogsAdapter;
import com.example.pettracker.Models.Owner;
import com.example.pettracker.Models.Pet;
import com.example.pettracker.Models.Task;
import com.example.pettracker.R;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 125;
    private RecyclerView rvCalSunday;
    private RecyclerView rvCalMonday;
    private RecyclerView rvCalTuesday;
    private RecyclerView rvCalWednesday;
    private RecyclerView rvCalThursday;
    private RecyclerView rvCalFriday;
    private RecyclerView rvCalSaturday;
    private RecyclerView rvDogList;
    private Button switchCal;
    private Button btnLogout;
    private Button btnAddDog;
    private TextView calViewType;
    private ImageView ivAddTask;
    DogsAdapter dogsAdapter;
    private CalendarAdapter calendarAdapterSun;
    private CalendarAdapter calendarAdapterMon;
    private CalendarAdapter calendarAdapterTue;
    private CalendarAdapter calendarAdapterWed;
    private CalendarAdapter calendarAdapterThu;
    private CalendarAdapter calendarAdapterFri;
    private CalendarAdapter calendarAdapterSat;
    private List<Pet> pets; //Need to get list of pets from database
    public List<Task> tasks;
    private List<Task> mondayTasks;
    private List<Task> tuesdayTasks;
    private List<Task> wednesdayTasks;
    private List<Task> thursdayTasks;
    private List<Task> fridayTasks;
    private List<Task> saturdayTasks;
    private List<Task> sundayTasks;
    public List<Owner> owners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pets = new ArrayList<>();
        tasks = new ArrayList<>();
        mondayTasks = new ArrayList<>();
        tuesdayTasks = new ArrayList<>();
        wednesdayTasks =  new ArrayList<>();
        thursdayTasks = new ArrayList<>();
        fridayTasks = new ArrayList<>();
        saturdayTasks = new ArrayList<>();
        sundayTasks = new ArrayList<>();
        owners = new ArrayList<>();
        rvCalSunday = (RecyclerView) findViewById(R.id.rvCalSunday);
        rvCalMonday = (RecyclerView) findViewById(R.id.rvCalMonday);
        rvCalTuesday = (RecyclerView) findViewById(R.id.rvCalTuesday);
        rvCalWednesday = (RecyclerView) findViewById(R.id.rvCalWednesday);
        rvCalThursday = (RecyclerView) findViewById(R.id.rvCalThursday);
        rvCalFriday = (RecyclerView) findViewById(R.id.rvCalFriday);
        rvCalSaturday = (RecyclerView) findViewById(R.id.rvCalSaturday);
        rvDogList = (RecyclerView) findViewById(R.id.rvDogList);
        switchCal = (Button) findViewById(R.id.btnSwitchCal);
        calViewType = (TextView) findViewById(R.id.tvCalView);
        ivAddTask = (ImageView) findViewById(R.id.ivAddTask);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnAddDog = (Button) findViewById(R.id.btnAddDog);


        ivAddTask.setImageDrawable(getResources().getDrawable(R.drawable.plus));
        calViewType.setText("This Week");
        switchCal.setText("Week");

        ivAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddTask(ParseUser.getCurrentUser());
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogout();
            }
        });

        btnAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDog();
            }
        });

        dogsAdapter = new DogsAdapter(this, pets);
        rvDogList.setAdapter(dogsAdapter);
        rvDogList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        loadPets(ParseUser.getCurrentUser());

        calendarAdapterSun = new CalendarAdapter(this, sundayTasks);
        rvCalSunday.setAdapter(calendarAdapterSun);
        rvCalSunday.setLayoutManager(new LinearLayoutManager(this));

        calendarAdapterMon = new CalendarAdapter(this, mondayTasks);
        rvCalMonday.setAdapter(calendarAdapterMon);
        rvCalMonday.setLayoutManager(new LinearLayoutManager(this));

        calendarAdapterTue = new CalendarAdapter(this, tuesdayTasks);
        rvCalTuesday.setAdapter(calendarAdapterTue);
        rvCalTuesday.setLayoutManager(new LinearLayoutManager(this));

        calendarAdapterWed = new CalendarAdapter(this, wednesdayTasks);
        rvCalWednesday.setAdapter(calendarAdapterWed);
        rvCalWednesday.setLayoutManager(new LinearLayoutManager(this));

        calendarAdapterThu = new CalendarAdapter(this, thursdayTasks);
        rvCalThursday.setAdapter(calendarAdapterThu);
        rvCalThursday.setLayoutManager(new LinearLayoutManager(this));

        calendarAdapterFri = new CalendarAdapter(this, fridayTasks);
        rvCalFriday.setAdapter(calendarAdapterFri);
        rvCalFriday.setLayoutManager(new LinearLayoutManager(this));

        calendarAdapterSat = new CalendarAdapter(this, saturdayTasks);
        rvCalSaturday.setAdapter(calendarAdapterSat);
        rvCalSaturday.setLayoutManager(new LinearLayoutManager(this));

        loadTasks(ParseUser.getCurrentUser());

        rvCalSunday.addItemDecoration(new DividerItemDecoration(rvCalSunday.getContext(), DividerItemDecoration.HORIZONTAL));
        rvCalSunday.addItemDecoration(new DividerItemDecoration(rvCalSunday.getContext(), DividerItemDecoration.VERTICAL));
        rvCalMonday.addItemDecoration(new DividerItemDecoration(rvCalMonday.getContext(), DividerItemDecoration.HORIZONTAL));
        rvCalMonday.addItemDecoration(new DividerItemDecoration(rvCalMonday.getContext(), DividerItemDecoration.VERTICAL));
        rvCalTuesday.addItemDecoration(new DividerItemDecoration(rvCalTuesday.getContext(), DividerItemDecoration.HORIZONTAL));
        rvCalTuesday.addItemDecoration(new DividerItemDecoration(rvCalTuesday.getContext(), DividerItemDecoration.VERTICAL));
        rvCalWednesday.addItemDecoration(new DividerItemDecoration(rvCalWednesday.getContext(), DividerItemDecoration.HORIZONTAL));
        rvCalWednesday.addItemDecoration(new DividerItemDecoration(rvCalWednesday.getContext(), DividerItemDecoration.VERTICAL));
        rvCalThursday.addItemDecoration(new DividerItemDecoration(rvCalThursday.getContext(), DividerItemDecoration.HORIZONTAL));
        rvCalThursday.addItemDecoration(new DividerItemDecoration(rvCalThursday.getContext(), DividerItemDecoration.VERTICAL));
        rvCalFriday.addItemDecoration(new DividerItemDecoration(rvCalFriday.getContext(), DividerItemDecoration.HORIZONTAL));
        rvCalFriday.addItemDecoration(new DividerItemDecoration(rvCalFriday.getContext(), DividerItemDecoration.VERTICAL));
        rvCalSaturday.addItemDecoration(new DividerItemDecoration(rvCalSaturday.getContext(), DividerItemDecoration.HORIZONTAL));
        rvCalSaturday.addItemDecoration(new DividerItemDecoration(rvCalSaturday.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void addDog() {
        Intent intent = new Intent(this, AddDogActivity.class);
        startActivity(intent);
    }

    private void onLogout() {
        finish();
        ParseUser.logOut();
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this makes sure the Back button won't work
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    private void goOwnerDetails() {//need to fix
        Intent intent = new Intent(this, OwnerInfoActivity.class);
        startActivity(intent);
    }

    private void loadTasks(ParseUser householdID) {
        ParseQuery<Owner> query = ParseQuery.getQuery(Owner.class);
        query.whereEqualTo(Owner.KEY_HOUSEHOLD_ID, householdID);
        query.findInBackground(new FindCallback<Owner>() {
            @Override
            public void done(List<Owner> queryOwners, ParseException e) {
                if(e != null)
                    return;
                owners.addAll(queryOwners);

                //loads Tasks after owners loaded
                ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
                for(Owner owner: owners) {
                    query.whereEqualTo(Task.KEY_OWNER_ID, owner.getObjectId());
                    query.findInBackground(new FindCallback<Task>() {
                        @Override
                        public void done(List<Task> queryTasks, ParseException e) {
                            if(e != null)
                                return;
                            tasks.addAll(queryTasks);
                            for(Task task:queryTasks) {
                                if(task.getDay().equals("Sunday"))
                                    sundayTasks.add(task);
                                if(task.getDay().equals("Monday"))
                                    mondayTasks.add(task);
                                if(task.getDay().equals("Tuesday"))
                                    tuesdayTasks.add(task);
                                if(task.getDay().equals("Wednesday"))
                                    wednesdayTasks.add(task);
                                if(task.getDay().equals("Thursday"))
                                    thursdayTasks.add(task);
                                if(task.getDay().equals("Friday"))
                                    fridayTasks.add(task);
                                if(task.getDay().equals("Saturday"))
                                    saturdayTasks.add(task);
                            }
                            calendarAdapterSun.notifyDataSetChanged();
                            calendarAdapterMon.notifyDataSetChanged();
                            calendarAdapterTue.notifyDataSetChanged();
                            calendarAdapterWed.notifyDataSetChanged();
                            calendarAdapterThu.notifyDataSetChanged();
                            calendarAdapterFri.notifyDataSetChanged();
                            calendarAdapterSat.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    private void loadPets(ParseUser householdID) {
        ParseQuery<Pet> query = ParseQuery.getQuery(Pet.class);
        query.whereEqualTo(Pet.KEY_HOUSEHOLD_ID, householdID);
        query.findInBackground(new FindCallback<Pet>() {
            @Override
            public void done(List<Pet> queryPets, ParseException e) {
                if(e != null)
                    return;
                pets.addAll(queryPets);
                dogsAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onAddTask(ParseUser householdID) {
        if(tasks.size() == 0){ //Initial setup
            Intent intent = new Intent(this, SetupTasksActivity.class);
            intent.putExtra("pets", Parcels.wrap(pets));
            intent.putExtra("owners", Parcels.wrap(owners));
            startActivity(intent);
        }
        else {
            showTaskAlert();
        }
    }

    private void showTaskAlert() {
        final Owner[] taskOwner = new Owner[1];
        final String[] taskTime = {new String()};
        final String[] taskDay = new String[1];
        final String[] taskDescription = {new String()};

        View  messageView = LayoutInflater.from(this).inflate(R.layout.message_task, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(messageView);
        final AlertDialog alertDialog = alertDialogBuilder.create();

        List<String> ownerNames = new ArrayList<>();
        for(Owner owner: owners)
            ownerNames.add(owner.getOwnerName());

        Spinner spinnerOwners = (Spinner) messageView.findViewById(R.id.spinnerOwners);
        ArrayAdapter adapterOwners = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ownerNames);
        adapterOwners.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOwners.setAdapter(adapterOwners);
        spinnerOwners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                taskOwner[0] = owners.get(ownerNames.indexOf(parent.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                taskOwner[0] = null;
            }
        });

        String[] days = getResources().getStringArray(R.array.days);
        Spinner spinnerDay = (Spinner) messageView.findViewById(R.id.spinnerDays);
        ArrayAdapter adapterDays = new ArrayAdapter(this, android.R.layout.simple_spinner_item, days);
        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapterDays);
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                taskDay[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                taskDay[0] = null;
            }
        });

        String[] hours = getResources().getStringArray(R.array.hours);
        Spinner spinnerHour = (Spinner) messageView.findViewById(R.id.spinnerHours);
        ArrayAdapter adapterHours = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hours);
        adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(adapterHours);
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                taskTime[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                taskTime[0] = null;
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Create Task", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                taskDescription[0] = ((EditText) alertDialog.findViewById(R.id.etDescription)).getText().toString();
                if(taskOwner[0] == null || taskDay[0] == null || taskTime[0] == null || taskDescription[0].length() == 0) {
                    Toast.makeText(MainActivity.this, "Make sure all fields are entered", Toast.LENGTH_SHORT).show();
                    return;
                }
                addTask(taskOwner[0], taskDescription[0], taskDay[0], taskTime[0]);
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void addTask(Owner owner, String taskTitle, String taskDay, String taskTime) {
        Task task = new Task();
        task.setTaskDescription(taskTitle);
        task.setDay(taskDay);
        task.setTime(taskTime);
        task.setOwnerId(owner.getObjectId());
        task.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null)
                    Toast.makeText(MainActivity.this, "Error adding task", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,"Task added", Toast.LENGTH_SHORT).show();
            }
        });
        tasks.add(task);
        calendarAdapterSun.notifyDataSetChanged();
        calendarAdapterMon.notifyDataSetChanged();
        calendarAdapterTue.notifyDataSetChanged();
        calendarAdapterWed.notifyDataSetChanged();
        calendarAdapterThu.notifyDataSetChanged();
        calendarAdapterFri.notifyDataSetChanged();
        calendarAdapterSat.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Pet pet = Parcels.unwrap(data.getParcelableExtra("petToDelete"));
            pets.remove(pet);
            pet.deleteInBackground();
            dogsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        calendarAdapterSun.notifyDataSetChanged();
        calendarAdapterMon.notifyDataSetChanged();
        calendarAdapterTue.notifyDataSetChanged();
        calendarAdapterWed.notifyDataSetChanged();
        calendarAdapterThu.notifyDataSetChanged();
        calendarAdapterFri.notifyDataSetChanged();
        calendarAdapterSat.notifyDataSetChanged();
    }
}