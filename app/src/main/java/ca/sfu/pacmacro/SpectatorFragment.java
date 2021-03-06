package ca.sfu.pacmacro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.soulwolf.widget.materialradio.MaterialRadioGroup;

import ca.sfu.pacmacro.Controller.CharacterDisplayCriteria;


public class SpectatorFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    public SpectatorFragment() {
        // Required empty public constructor
    }

    public static SpectatorFragment newInstance() {
        return new SpectatorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spectator, container, false);

        MaterialRadioGroup teamSelection = (MaterialRadioGroup) view.findViewById(R.id.team_selection);

        Button startButton = (Button) view.findViewById(R.id.spectator_start);
        startButton.setOnClickListener(getStartButtonListener(teamSelection));

        return view;
    }

    public View.OnClickListener getStartButtonListener(final MaterialRadioGroup teamSelection) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int teamId = teamSelection.getCheckedRadioButtonId();
                switch(teamId) {
                    case R.id.team_ghost:
                        goToSpectatorActivity(CharacterDisplayCriteria.CRITERIA_GHOST_TEAM);
                        break;
                    case R.id.team_pacman:
                        AlertDialog.Builder confirmSelectionDialogBuilder = new AlertDialog.Builder(SpectatorFragment.this.getContext());
                        confirmSelectionDialogBuilder.setTitle(R.string.dialog_title_confirm_team);
                        confirmSelectionDialogBuilder.setMessage(R.string.dialog_message_pacman_confirm_team);
                        confirmSelectionDialogBuilder.setNegativeButton(R.string.dialog_button_cancel, null);
                        confirmSelectionDialogBuilder.setPositiveButton(R.string.dialog_button_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                goToSpectatorActivity(CharacterDisplayCriteria.CRITERIA_PACMAN_TEAM);
                            }
                        });
                        confirmSelectionDialogBuilder.show();
                        break;
                    default:
                        AlertDialog.Builder makeSelectionDialogBuilder = new AlertDialog.Builder(SpectatorFragment.this.getContext());
                        makeSelectionDialogBuilder.setTitle(R.string.dialog_title_select_team);
                        makeSelectionDialogBuilder.setPositiveButton(R.string.dialog_button_ok, null);
                        makeSelectionDialogBuilder.show();
                }
            }
        };
    }

    private void goToSpectatorActivity(int selectedTeam) {
        Intent intent = new Intent(getContext(), SpectatorActivity.class);
        intent.putExtra(CharacterDisplayCriteria.EXTRA_KEY, selectedTeam);
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
