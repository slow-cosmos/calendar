
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.Calendar;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class cal extends JFrame {

	JComboBox cbyear,cbmonth;
	JLabel imgLabel,l,whatD;
	JLabel[] datel;
	JPanel p;
	int year1, month1, dayNum;
	int numY, numM,numD;
	
	int[] date = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	
	Calendar calendar = Calendar.getInstance();
	int todayY=calendar.get(Calendar.YEAR);
	int todayM=calendar.get(Calendar.MONTH); //0������ ����
	
	ImageIcon[] images = {
			new ImageIcon("images/1.jpg"),
			new ImageIcon("images/2.jpg"),
			new ImageIcon("images/3.jpg"),
			new ImageIcon("images/4.jpg"),
			new ImageIcon("images/5.jpg"),
			new ImageIcon("images/6.jpg"),
			new ImageIcon("images/7.jpg"),
			new ImageIcon("images/8.jpg"),
			new ImageIcon("images/9.jpg"),
			new ImageIcon("images/10.jpg"),
			new ImageIcon("images/11.jpg"),
			new ImageIcon("images/12.jpg")
	};
	
	
	
	
	
	cal(){
		setTitle("������ �޷�");
		setLayout(new BorderLayout(10,10));


		String[] year= {"2015","2016","2017","2018","2019","2020",
				"2021","2022","2023","2024","2025"};
		String[] month= {"1","2","3","4","5","6","7","8","9","10","11","12"};
		
		//�޺��ڽ�(���)
		
		JPanel p1 = new JPanel(new FlowLayout());
		
		cbyear = new JComboBox(year);  
		cbmonth= new JComboBox(month);

		cbyear.setSelectedIndex(todayY-2015); //�޺��ڽ� �ʱⰪ �������ֱ�
		cbmonth.setSelectedIndex(todayM);
		
		JLabel l1 = new JLabel("��   ");
		JLabel l2 = new JLabel("��");
		
		p1.add(cbyear);
		p1.add(l1);
		p1.add(cbmonth);
		p1.add(l2);


		add(p1,BorderLayout.NORTH);
	
		//-----------------�̹���(�߰�)--------------------
		
		JPanel p2 = new JPanel(null);
		
		Font font = new Font("����",Font.ITALIC,15);
		
		
		whatD = new JLabel("");
		whatD.setBounds(20,30,400,100);
		whatD.setFont(font);

		
		imgLabel = new JLabel(images[todayM]);
		imgLabel.setBounds(65,5,150,150);
		
		

		p2.add(whatD);
		p2.add(imgLabel);				
		

		
		add(p2, BorderLayout.CENTER);
		
		//-----------------�޷�(�ϴ�)---------------------
		
		p = new JPanel(new GridLayout(0,7));
		
		String[] days= {"��","��","ȭ","��","��","��","��"};
		
		JLabel[] day=new JLabel[7];
		
		for(int i=0;i<7;i++) //���� ǥ��
		{
			day[i]=new JLabel(days[i]);
			p.add(day[i]);
		}

		
		datel=new JLabel[37]; //�� �޿� �ִ� �ִ� �� �� + 1�� ���� �ִ� 6ĭ
		
		
		month1=date[cbmonth.getSelectedIndex()]; //���� ��������
		
		int setY = cbyear.getSelectedIndex();
		
		if((setY==1||setY==5||setY==9)
				&&cbmonth.getSelectedIndex()==1)
			month1 = 29;

		
		calendar.set(Calendar.YEAR,cbyear.getSelectedIndex()+2015 ); //�ʱ� ����
		calendar.set(Calendar.MONTH,cbmonth.getSelectedIndex()); 
		calendar.set(Calendar.DATE,1); //���� �� 1�� ����
		dayNum=calendar.get(Calendar.DAY_OF_WEEK); //1�� ���� ���ϱ�

		//�޷� ǥ��
		for(int i=0;i<dayNum-1;i++) //1�� �� ����
		{
			datel[i]=new JLabel("");
			p.add(datel[i]);
		}
		
		for(int i=1;i<=month1;i++) //1�Ϻ��� ���ϱ���
		{
			datel[dayNum-2+i]=new JLabel(Integer.toString(i));
			p.add(datel[dayNum-2+i]);
		}
		
		for(int i=month1+dayNum-1;i<37;i++) //���� �� ����
		{
			datel[i]=new JLabel("");
			p.add(datel[i]);
		}
	

		
		add(p, BorderLayout.SOUTH);
		
		//-----------�̺�Ʈ-------------
		
		//�� �޺��ڽ� �̺�Ʈ
		cbyear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox cb=(JComboBox)e.getSource();
				int index = cb.getSelectedIndex();
				
				calendar.set(Calendar.YEAR,index+2015 ); //�ʱ� ����
				calendar.set(Calendar.MONTH,cbmonth.getSelectedIndex()); 
				calendar.set(Calendar.DATE,1); //���� �� 1�� ����
				dayNum=calendar.get(Calendar.DAY_OF_WEEK);
				
				month1 = date[cbmonth.getSelectedIndex()];

				if(((index+2015)%4==0&&(index+2015)%100!=0||(index+2015)%400==0)
						&&cbmonth.getSelectedIndex()==1)
					month1 = 29;
				
				for(int i =0;i<dayNum-1;i++) //1�� �� ����	
				{
					datel[i].setText("");
				}
			
				for(int i=1;i<=month1;i++) //1�Ϻ��� ���ϱ���
				{
					datel[dayNum-2+i].setText(Integer.toString(i));
				}	
				
				for(int i=month1+dayNum-1;i<37;i++)
				{
					datel[i].setText("");
				}

			
			}		
		});
		
		
		//�� �޺��ڽ� �̺�Ʈ
		cbmonth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox cb=(JComboBox)e.getSource();
				int index = cb.getSelectedIndex();
				
				
				imgLabel.setIcon(images[index]); 

				calendar.set(Calendar.YEAR,cbyear.getSelectedIndex()+2015);
				calendar.set(Calendar.MONTH,index); 
				calendar.set(Calendar.DATE,1); //���� �� 1�� ����
				dayNum=calendar.get(Calendar.DAY_OF_WEEK);
				
				month1=date[index];

				if((((cbyear.getSelectedIndex()+2015)%4==0&&(cbyear.getSelectedIndex()+2015)%100!=0||(cbyear.getSelectedIndex()+2015)%400==0))&&index==1)
					month1 = 29;
				
				for(int i =0;i<dayNum-1;i++) //1�� �� ����	
				{
					datel[i].setText("");
				}
			
				for(int i=1;i<=month1;i++) //1�Ϻ��� ���ϱ���
				{
					datel[dayNum-2+i].setText(Integer.toString(i));
				}	
				
				for(int i=month1+dayNum-1;i<37;i++)
				{
					datel[i].setText("");
				}
			
			}
		});
		
		//�¾�� ��ĥ �̺�Ʈ
		MouseListener mouse = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel la = (JLabel)e.getSource();
				int laNum = Integer.parseInt(la.getText()); // �� ��ȯ
				for(int i=1; i<=31;i++)
				{
					if(laNum==i)
					{
						numY = cbyear.getSelectedIndex()+2015 - 1999; 
						numM = cbmonth.getSelectedIndex()+1-10;
						numD = laNum-30;
						
						if(numD<0){
							numD=numD+31;
							numM=numM-1;
						}
						if(numM<0){
							numM=numM+12;
							numY=numY-1;
						}
						
						 JOptionPane.showMessageDialog(null, "���� �¾�� "+numY+"�� "+numM+"���� "+numD+"�� �� �Դϴ�.", 
								 "1999�� 10�� 30�Ͽ� �¾���ϴ�!", JOptionPane.WARNING_MESSAGE);

						break;
					}
				}
				if(la.getText()=="")
				{
					l.setText(" ");
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		for(int i=0;i<37;i++)
		{
			datel[i].addMouseListener(mouse);
		}
		
		
		//���� ���� �̺�Ʈ
		MouseListener mouse1 = new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel la = (JLabel)e.getSource();
				int laNum = Integer.parseInt(la.getText()); // �� ��ȯ
				
				for(int i=1; i<=31;i++)
				{
					if(laNum==i)
					{
						calendar.set(Calendar.DATE,laNum);
						int dayN=calendar.get(Calendar.DAY_OF_WEEK); //1�� ���� ���ϱ�
						whatD.setText((cbyear.getSelectedIndex()+2015)+"�� "+
						(cbmonth.getSelectedIndex()+1)+"�� "+laNum+"���� "+ days[dayN-1]+"���� �Դϴ�.");

					}
				}
				if(la.getText()=="")
				{
					whatD.setText(" ");
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				whatD.setText("");
			}
		};
		
		for(int i=0;i<37;i++)
		{
			datel[i].addMouseListener(mouse1);
		}
		
		//---------------------------------------------------
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,380);
		setVisible(true);
		setResizable(false);

	
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new cal();
	}

}
