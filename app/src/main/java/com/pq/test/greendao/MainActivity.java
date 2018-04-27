package com.pq.test.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.pq.test.R;
import com.pq.test.greendao.dao.StudentDao;
import com.pq.test.greendao.db.BaseDbHelper;
import com.pq.test.greendao.db.GreenDaoDb;
import com.pq.test.greendao.entity.Student;


import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * <pre>
 *     author : chenzengqiang
 *     e-mail : chenzengqiang@hongdoujiao.com
 *     time   : 2018/03/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.add)
    Button add;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.updata)
    Button updata;
    @BindView(R.id.check)
    Button check;
    @BindView(R.id.deleteAll)
    Button deleteAll;
    @BindView(R.id.check_id)
    Button checkId;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private BaseDbHelper<Student, Long> studentLongBaseDbHelper;
    private List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greendao_activity_test);
        ButterKnife.bind(this);
        studentLongBaseDbHelper = GreenDaoDb.getDaoHelper(GreenDaoDb.getDaoSession(this).getStudentDao());
        initListener();
    }


    private void initListener() {
        /**
         *增
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 100; i++) {
                    Student stu = new Student();
                    stu.name = "Nauto_" + (1);
                    stu.age = "555"+i;
                    stu.number = 6 + "" + (1);
                    stu.score = "uuu ";
                    studentList.add(stu);
                }
                studentLongBaseDbHelper.save(studentList);
                studentList.clear();
            }
        });
        /**
         * 删
         */
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         *删除所有
         */
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentLongBaseDbHelper.deleteAll();
            }
        });
        /**
         * 更新
         */
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query<Student> query = studentLongBaseDbHelper.queryBuilder().where(StudentDao.Properties.Id.eq(10))
                        .build();
                Student student = query.unique();
                student.age = "00000000";
                studentLongBaseDbHelper.update(student);
            }
        });
        /**
         * 查询全部
         */
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> students = studentLongBaseDbHelper.queryAll();
                for (Student student : students) {
                    Log.e("test", student.toString());
                }
            }
        });

        checkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
