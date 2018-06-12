package com.example.puppy.subwayapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {

    EditText editID, editPW, editName, editTel, editAge, editAddr,editPWConfirm;
    String result, equals;
    Button btn;
    CheckBox checkBox;
    public SignUp() {
        // Required empty public constructor
    }

    //비밀번호 정규식
    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM
            = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$"); // 4자리 ~ 16자리까지 가능
    //비밀번호 검사
    public static boolean validatePassword(String pwStr){
        Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(pwStr);
        return matcher.matches();
    }

    public static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_sign_up, container, false);
        editID = root.findViewById(R.id.editID);
        editPW = root.findViewById(R.id.editPW);
        editName = root.findViewById(R.id.editName);
        editTel = root.findViewById(R.id.editTel);
        editAge = root.findViewById(R.id.editAge);
        editAddr = root.findViewById(R.id.editAddr);
        editPWConfirm = root.findViewById(R.id.editPWConfirm);
        btn = root.findViewById(R.id.button5);
        checkBox = root.findViewById(R.id.checkBox);

        btn.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("회원가입").setMessage("가입 하시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*
                    회원가입 검증

                    */
                    //입력 여부검증
                    if(editID.getText().toString().isEmpty()||editTel.getText().toString().isEmpty()||
                            editPW.getText().toString().isEmpty()||editName.getText().toString().isEmpty()||
                            editAge.getText().toString().isEmpty()||editAddr.getText().toString().isEmpty())
                    {
                        Toast.makeText(getActivity(),"입력되지 않은 값이 있습니다",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //나이 숫자 여부 판단
                        if (isStringDouble(editAge.getText().toString())){
                            if(!editPW.getText().toString().equals(editPWConfirm.getText().toString())){
                                Toast.makeText(getActivity(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                                editPWConfirm.requestFocus();
                            }
                            else{
                                //체크박스 체크여부 판단
                                if(checkBox.isChecked()){
                                    //비밀번호 정규화
                                    if(validatePassword(editPW.getText().toString())){
                                        MainActivity activity = (MainActivity)getActivity();
                                        activity.onFragmentChanged("HomeMenu");
                                        Toast.makeText(getActivity(),"가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(),"비밀번호는 정규표현식을 제외한 4자리에서 16자리만 가능합니다.",Toast.LENGTH_SHORT).show();
                                    }



                                }else{
                                    Toast.makeText(getActivity(),"개인정보 동의 여부를 체크해야 가입이 가능합니다.",Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                        else{
                            Toast.makeText(getActivity(),"나이에는 숫자만 입력해주세요",Toast.LENGTH_SHORT).show();
                            editAge.requestFocus();
                        }
                    }
                }
            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(),"취소 되었습니다.",Toast.LENGTH_SHORT).show();
                }
            }).show();
        });




        return root;
    }

}
