package com.example.m8app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.m8app.Model.Dioses;
import com.example.m8app.DB.DiosesDBHelper;


public class FormFragment extends Fragment {
    private DiosesDBHelper dbHelper;
    private SQLiteDatabase db;
    public FormFragment() {
        // Required empty public constructor
    }
    public FormFragment(DiosesDBHelper dbHelper,SQLiteDatabase db) {
        this.dbHelper=dbHelper;
        this.db=db;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Declarations
        View formdioses = inflater.inflate(R.layout.fragment_form,container,false);
        final Button buttonAñadir = formdioses.findViewById(R.id.btnAñadir);
        final Button buttonEliminar = formdioses.findViewById(R.id.btnEliminar);
        final EditText Nombre = formdioses.findViewById(R.id.NameDios);
        //
        //Create Spinner
        Spinner spinnerPanteon = (Spinner) formdioses.findViewById(R.id.spinnerPanteon);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.SpinnerPantheon, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPanteon.setAdapter(adapter);
        //Create Spinner 2
        Spinner spinnerRol = (Spinner) formdioses.findViewById(R.id.spinnerRol);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.SpinnerRol, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRol.setAdapter(adapter1);
        //Create Spinner 3
        Spinner spinnerRango = (Spinner) formdioses.findViewById(R.id.spinnerRango);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.SpinnerRango, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRango.setAdapter(adapter2);
        //Create Spinner 4
        Spinner spinnerDaño = (Spinner) formdioses.findViewById(R.id.spinnerDaño);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.SpinnerDaño, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDaño.setAdapter(adapter3);


        //When you click the button it calls the functions OnClickListener
        buttonAñadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getResources().getString(R.string.Crear_Dios));
                builder.setMessage(getResources().getString(R.string.MensajeConfirmacionCrear));
                builder.setPositiveButton(getResources().getString(R.string.SiCrear),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Dioses dios = new Dioses(Nombre.getText().toString(),
                                        spinnerPanteon.getSelectedItem().toString(),
                                        spinnerRol.getSelectedItem().toString(),
                                        spinnerRango.getSelectedItem().toString(),
                                        spinnerDaño.getSelectedItem().toString());
                                dbHelper.insertDioses(db,dios);
                                Toast.makeText(getActivity(),getResources().getString(R.string.CorrectoMensajeCrear), Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(getResources().getString(R.string.NoCrear), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                builder.show();


            }
        });
        //When you click the button it calls the functions OnClickListener
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getResources().getString(R.string.Eliminar_Dios));
                builder.setMessage(getResources().getString(R.string.MensajeConfirmacionEliminar));
                builder.setPositiveButton(getResources().getString(R.string.SiEliminar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHelper.delete();
                                Toast.makeText(getActivity(),getResources().getString(R.string.CorrectoMensajeElinimar), Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(getResources().getString(R.string.NoEliminar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                builder.show();

            }
        });


        return formdioses;
    }


}