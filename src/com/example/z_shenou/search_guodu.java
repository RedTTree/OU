package com.example.z_shenou;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
 
public class search_guodu extends Activity {
 
	  //����
	  private ImageButton search;
	  private String data;
	  
  private TextView back;
  private EditText et_search;
  private TextView tv_tip;
  private MyListView listView;
  private TextView tv_clear;
  private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);;
  private SQLiteDatabase db;
  private BaseAdapter adapter;
 
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.search_guodu);
    // ��ʼ���ؼ�
    data=null;
    initView();
    
    search.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO �Զ����ɵķ������
			if(data==null){
				Toast.makeText(search_guodu.this, "�������ѯ����", Toast.LENGTH_SHORT).show();
			}
			else{
			   Intent intent=new Intent(search_guodu.this,Search.class);
				intent.putExtra("search",data);
				startActivity(intent);
			}
		}
	});
 back.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		Intent itent=new Intent(search_guodu.this,MainActivity.class);
		startActivity(itent);
	}
});
    
    // ���������ʷ
    tv_clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        deleteData();
        queryData("");
      }
    });
 
    // ������ļ�������������ص�
    et_search.setOnKeyListener(new View.OnKeyListener() {// ������󰴼����ϵ�������
 
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// �޸Ļس�������
          // �����ؼ���
          ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
              getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
          // �����������󽫵�ǰ��ѯ�Ĺؼ��ֱ�������,����ùؼ����Ѿ����ھͲ�ִ�б���
          boolean hasData = hasData(et_search.getText().toString().trim());
          if (!hasData) {
            insertData(et_search.getText().toString().trim());
            queryData("");
          }
          data=et_search.getText().toString().trim();
          // TODO �������������ģ����ѯ��Ʒ������ת����һ������
          if(data==null){
				Toast.makeText(search_guodu.this, "�������ѯ����", Toast.LENGTH_SHORT).show();
			}
			else{
			   Intent intent=new Intent(search_guodu.this,Search.class);
				intent.putExtra("search", data);
				startActivity(intent);
			}
		    
          Toast.makeText(search_guodu.this, "clicked!", Toast.LENGTH_SHORT).show();
 
        }
        return false;
      }
    });
 
    // ��������ı��仯ʵʱ����
    et_search.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
 
      }
 
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
 
      }
 
      @Override
      public void afterTextChanged(Editable s) {
        if (s.toString().trim().length() == 0) {
          tv_tip.setText("������ʷ");
        } else {
          tv_tip.setText("�������");
        }
        String tempName = et_search.getText().toString();
        // ����tempNameȥģ����ѯ���ݿ�����û������
        queryData(tempName);
 
      }
    });
 
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        String name = textView.getText().toString();
        et_search.setText(name);
        Toast.makeText(search_guodu.this, name, Toast.LENGTH_SHORT).show();
        // TODO ��ȡ��item��������֣����ݸùؼ�����ת����һ��ҳ���ѯ�������Լ�ȥʵ��
        Intent intent=new Intent(search_guodu.this,Search.class);
		intent.putExtra("search", name);
		startActivity(intent);
      }
    });
 
    // �������ݣ����ڲ��ԣ�
    Date date = new Date();
    long time = date.getTime();
    insertData("Leo" + time);
 
    // ��һ�ν����ѯ���е���ʷ��¼
    queryData("");
  }
 
  /**
   * ��������
   */
  private void insertData(String tempName) {
    db = helper.getWritableDatabase();
    db.execSQL("insert into records(name) values('" + tempName + "')");
    db.close();
  }
 
  /**
   * ģ����ѯ����
   */
  private void queryData(String tempName) {
    Cursor cursor = helper.getReadableDatabase().rawQuery(
        "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
    // ����adapter����������
    adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
        new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    // ����������
    listView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
  }
  /**
   * ������ݿ����Ƿ��Ѿ��и�����¼
   */
  private boolean hasData(String tempName) {
    Cursor cursor = helper.getReadableDatabase().rawQuery(
        "select id as _id,name from records where name =?", new String[]{tempName});
    //�ж��Ƿ�����һ��
    return cursor.moveToNext();
  }
 
  /**
   * �������
   */
  private void deleteData() {
    db = helper.getWritableDatabase();
    db.execSQL("delete from records");
    db.close();
  }
 
  private void initView() {
	  search=(ImageButton)findViewById(R.id.ib_search);
	back=(TextView)findViewById(R.id.back);
    et_search = (EditText) findViewById(R.id.et_search);
    tv_tip = (TextView) findViewById(R.id.tv_tip);
    listView = (com.example.z_shenou.MyListView) findViewById(R.id.listView);
    tv_clear = (TextView) findViewById(R.id.tv_clear);
 
    
  }
}