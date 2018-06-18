package com.example.puppy.subwayapp;


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

import com.example.puppy.subwayapp.task.TaskPost;
import com.example.puppy.subwayapp.vo.ClientVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment
{
    int etId[] = {
            R.id.editID, R.id.editPW, R.id.editName,
            R.id.editTel, R.id.editAge, R.id.editAddr,
            R.id.editPWConfirm
    };

    EditText et[] = new EditText[etId.length];
    Button btn;
    CheckBox checkBox;

    public SignUp() {
        // Required empty public constructor
    }

    //비밀번호 정규식
    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM
            = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$"); // 4자리 ~ 16자리까지 가능

    //비밀번호 검사
    public static boolean validatePassword(String pwStr) {
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

    /**
     * 데이터를 서버로 보내서 DB에 insert 하는 메서드
     * @return 성공시 true, 실패시 false
     */
    public boolean sendData()
    {
        ObjectMapper mapper = new ObjectMapper();
        ClientVO vo = new ClientVO(et[0].getText().toString(), et[1].getText().toString(),et[2].getText().toString(),
                                   et[3].getText().toString(),et[5].getText().toString(),Integer.parseInt(et[4].getText().toString()));

        try {
            boolean output = new TaskPost("/api/joinClient.json",mapper.writeValueAsString(vo))
                                .execute().get();
            return output;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up, container, false);

        for (int i = 0; i < et.length; i++) {
            et[i] = (EditText) root.findViewById(etId[i]);
        }

        btn = root.findViewById(R.id.button5);
        checkBox = root.findViewById(R.id.checkBox);

        btn.setOnClickListener(v ->
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("회원가입").setPositiveButton("확인", (dialog12, which) ->
            {
                /*회원가입 검증*/

                //입력 여부검증
                /*
                    if(editID.getText().toString().isEmpty()||editTel.getText().toString().isEmpty()||
                        editPW.getText().toString().isEmpty()||editName.getText().toString().isEmpty()||
                            editAge.getText().toString().isEmpty()||editAddr.getText().toString().isEmpty())
                 */

                if (Arrays.stream(et)
                          .anyMatch(i -> i.getText().toString().isEmpty()))
                {
                    Toast.makeText(getActivity(), "입력되지 않은 값이 있습니다", Toast.LENGTH_SHORT).show();
                } else {
                    //나이 숫자 여부 판단
                    if (isStringDouble(et[4].getText().toString())) {
                        if (!et[1].getText().toString().equals(et[6].getText().toString())) {
                            Toast.makeText(getActivity(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                            et[6].requestFocus();
                        } else {
                            //체크박스 체크여부 판단
                            if (checkBox.isChecked()) {
                                //비밀번호 정규화
                                if (validatePassword(et[1].getText().toString())) {

                                } else {
                                    Toast.makeText(getActivity(), "비밀번호는 정규표현식을 제외한 4자리에서 16자리만 가능합니다.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "개인정보 동의 여부를 체크해야 가입이 가능합니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getActivity(), "나이에는 숫자만 입력해주세요", Toast.LENGTH_SHORT).show();
                        et[4].requestFocus();
                    }
                }
            }).setNegativeButton("취소", (dialog1, which)
                    -> Toast.makeText(getActivity(), "취소 되었습니다.", Toast.LENGTH_SHORT).show()).show();

            boolean result = sendData();

            if(result == true){
                Toast.makeText(getActivity(), "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged("HomeMenu");

            }else{
                Toast.makeText(getActivity(), "실패", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}