package seabattle;
import java.util.*;

class Cell {
int vertical;
char horizontal;
boolean b=false;
Ship sh=null;
Cell (int i,int j)
{
  char h='a';
  for (int z=0;z<i;z++)
  {
    h++;
  }
  vertical=j;
  horizontal=h;
}
char getHorizontal()
{
  return horizontal;
}
int getVertical() {
return vertical;
}
Ship getShip() {
return sh;
}
boolean checkCell(Cell c[][],int shn)  //проверка клетки
{
  int stv,ev;
  int sth,eh;
  boolean f=false;
  if (vertical==1) stv=1;
  else stv=vertical-1;
  if (vertical==10) ev=10;
  else ev=vertical+1;
  if (horizontal-'a'==0) sth=0;
  else sth=(horizontal-'a'-1);
  if (horizontal-'a'==9) eh=9;
  else eh=(horizontal-'a'+1);
  for (int i=stv;i<=ev;i++)
  {for (int j=sth;j<=eh;j++)
  {
    if (c[i-1][j].getShip()==null||c[i-1][j].getShip().getNumber()==shn) {
    
            }
    else {
        f=true;
        break;
    }
  }
    if (f) break;
  }
  return f;
}
void setShip(Ship sh)
{
  this.sh=sh;
}
boolean checkCrash () {
return b;
}
void setCrash () {
b=true;
}
}

class Ship {
int size;
int number;
Cell cell[];
Ship (int s,int n)
{
 size=s;
 number=n;
 cell=new Cell[size];
 for (int i=0;i<size;i++)
 cell[i]=null;
}
int getSize() {
 return cell.length;
}
int getNumber() {
return number;
}
boolean getFree() 
{
  if (cell[0]==null) return true;
  return false;
}
void setShip(Cell[][] c,int sh,int eh,int sv,int ev) 
{
  for (int i=0;i<size;i++)
  {
    if (cell[i]!=null) cell[i].setShip(null);
    cell[i]=null;
  }
  int dh=eh-sh,dv=ev-sv;
  int ph,pv;
  for (int z=0;z<size;z++)
  {
    if (dh==0) ph=sh;
    else {ph=sh+dh;
        if (dh>0) dh--;
        else dh++;
         }
    if (dv==0) pv=sv;
    else {pv=sv+dv;
         if (dv>0) dv--;
         else dv++;
         } 
    cell[z]=c[pv][ph];
    cell[z].setShip(this);
  }
   
}
boolean checkCrash () {
for (Cell x: cell) {
if (!x.checkCrash()) return false;
}
return true;
}
void setArea (Cell[][] c) {
int sv,ev,sh,eh;
sv=(cell[0].getVertical()<cell[size-1].getVertical())?cell[0].getVertical():cell[size-1].getVertical();
sv-=2;
if (sv<0) sv=0;
ev=(cell[0].getVertical()>cell[size-1].getVertical())?cell[0].getVertical():cell[size-1].getVertical();
if (ev>9) ev=9;
sh=(cell[0].getHorizontal()<cell[size-1].getHorizontal())?cell[0].getHorizontal()-'a':cell[size-1].getHorizontal()-'a';
sh--;
if (sh<0) sh=0;
eh=(cell[0].getHorizontal()>cell[size-1].getHorizontal())?cell[0].getHorizontal()-'a':cell[size-1].getHorizontal()-'a';
eh++;
if (eh>9) eh=9;

for (int i=sv;i<=ev;i++)
for (int j=sh;j<=eh;j++)
{
  c[i][j].setCrash();
}
}
}

public class SeaBattle {
static void drawS(Ship[] sh,Cell[][] c) { //отрисовка
for (int i=0;i<11;i++)   
{for (int j=0;j<22;j++)
{
  if (i==0&&j==0) System.out.print(" ");
  if (j==1&&i<10) System.out.print(" ");
  if (j%2==1) System.out.print(" ");
  if (i==0&&j%2==0&&j!=0) System.out.print(c[0][j/2-1].getHorizontal());
  if (j==0&&i!=0) System.out.print(c[i-1][0].getVertical());
  if (j%2==0&&j!=0&&i!=0) if (c[i-1][(j-1)/2].getShip()==null) System.out.print(" ");
  else System.out.print(c[i-1][(j-1)/2].getShip().getNumber());
}
 System.out.print("  ");
 if (i>0) 
 for (int z=0;z<sh[i-1].getSize()&&sh[i-1].getFree();z++)
 System.out.print(sh[i-1].getNumber());
 System.out.println();
}
}

static void drawP (Ship[] shp,Cell[][] cp, Ship[] shc, Cell[][] cc )
{
  for (int i=0;i<11;i++)   
  {  for (int j=0;j<22;j++)
    {
      if (i==0&&j==0) System.out.print(" ");
      if (j==1&&i<10) System.out.print(" ");
      if (j%2==1) System.out.print(" ");
      if (i==0&&j%2==0&&j!=0) System.out.print(cp[0][j/2-1].getHorizontal());
      if (j==0&&i!=0) System.out.print(cp[i-1][0].getVertical());
      if (j%2==0&&j!=0&&i!=0) if (cp[i-1][(j-1)/2].getShip()==null) if (cp[i-1][(j-1)/2].checkCrash()) System.out.print("*");
                                                                    else System.out.print(" ");
                              else { if (cp[i-1][(j-1)/2].getShip().checkCrash()) System.out.print("X");
                                     else if (cp[i-1][(j-1)/2].checkCrash()) System.out.print("+");
                                          else System.out.print(cp[i-1][(j-1)/2].getShip().getNumber());
                                   }
    }
     System.out.print("     ");
     for (int j=0;j<22;j++)
    {
     if (i==0&&j==0) System.out.print(" ");
     if (j==1&&i<10) System.out.print(" ");
     if (j%2==1) System.out.print(" ");
     if (i==0&&j%2==0&&j!=0) System.out.print(cc[0][j/2-1].getHorizontal());
     if (j==0&&i!=0) System.out.print(cc[i-1][0].getVertical());
     if (j%2==0&&j!=0&&i!=0) if (cc[i-1][(j-1)/2].getShip()==null) if (cc[i-1][(j-1)/2].checkCrash()) System.out.print("*");
                                                                   else System.out.print(" ");
                             else {if (cc[i-1][(j-1)/2].getShip().checkCrash()) System.out.print("X");
                                   else if (cc[i-1][(j-1)/2].checkCrash()) System.out.print("+");
                                        else System.out.print(" ");
                                  }
    }
     System.out.println();
  }
}
static void Set(Ship[] sh,Cell[][] c, boolean s,Scanner sc) { //установка кораблей
  boolean b=true;
  int i=0;
  while (b) {
  if (s) drawS(sh,c);
  int shn;
  do { //ввод номера корабля
  if (s) {
  try {
   String shns;
   System.out.print("Введите номер корабля: ");
   shns=sc.nextLine();
   shn=Integer.parseInt(shns);
  }
  catch (Exception e) {
   shn=-1;
  }
  }
  else {
   shn=i;
   i++;
  }
  if (shn>=0&&shn<10) break;
  else System.out.println("Некоректный номер корабля");
  } while (true);
  do { 
   String st;
   char ch;
   int in;
   if (s) {
   try {
   System.out.print("Введите начальную позицию: ");
   st=sc.nextLine();
   if (st.length()<2||st.length()>3) {
                                       System.out.println("Некоректная позиция");
                                       continue;
                                     }
   ch=st.charAt(0);
   if (ch<'a'||ch>'j')  {
                          System.out.println("Некоректная позиция");
                          continue;
                        }
   st=st.substring(1);
   in=Integer.parseInt(st);
   }
   catch (Exception e) {
    in=0;
    ch='z';
   }
   if (in<1||in>10) {
                      System.out.println("Некоректная позиция");
                      continue;
                    }
   }
   else {
     ch='a';
     ch+=(int)(Math.random()*10);
     in=1;
     in+=(int)(Math.random()*10);
   }
   if (c[in-1][(int)(ch-'a')].checkCell(c,shn)) {
                                                  if (s) System.out.println("Позиция занята");
                                                  continue;
                                                }
   if (sh[shn].getSize()==1) sh[shn].setShip(c,(int) (ch-'a'), (int) (ch-'a'),in-1 , in-1);
   else {
   char che;
   int ine;
   if (s) {
   try {
    System.out.print("Введите конечную позицию: ");
    st=sc.nextLine();
    if (st.length()<2||st.length()>3) {
                                       System.out.println("Некоректная позиция");
                                       continue;
                                      }
    che=st.charAt(0);
    st=st.substring(1);
    ine=Integer.parseInt(st);
   }
   catch (Exception e) {
    ine=-1;
    che='z';
   }
   }
   else {
    if (Math.random()<0.25) {
                              che=ch;
                              ine=in+sh[shn].getSize()-1;
                            }
    else if (Math.random()<0.5) {
                                 che=ch;
                                 ine=in-sh[shn].getSize()+1;
                                }
         else if (Math.random()<0.75) {
                                       che=ch;
                                       che+=(sh[shn].getSize()-1);
                                       ine=in;
                                      }
              else {
                     che=ch;
                     che-=(sh[shn].getSize()+1);
                     ine=in;
                   }
   }
   if (che<'a'||che>'j')  {
                           if (s) System.out.println("Некоректная позиция");
                           continue;
                          }
   if (ine<1||ine>10) {
                        if (s) System.out.println("Некоректная позиция");
                        continue;
                      } 
   if (!((che==ch^ine==in)&&(Math.abs(che-ch)==(sh[shn].getSize()-1)^Math.abs(ine-in)==(sh[shn].getSize()-1)))) {
                                                if (s) System.out.println("Некоректная позиция ");
                                                continue;
                                              }
    
   int dh=che-ch,dv=ine-in;
   int ph=0,pv=0;
   boolean f=false;
   for (int z=0;z<sh[shn].getSize();z++)
  {
    if (dh==0) ph=(int)(ch-'a');
    else {ph=(int)(ch-'a')+dh;
          if (dh>0) dh--;
          else dh++;
         }
    if (dv==0) pv=in-1;
    else {pv=in-1+dv;
          if (dv>0) dv--;
          else dv++;
         } 
    
    if ( c[pv][ph].checkCell(c, shn)) {f=true;
                                       break;
                                      
                                      }
  }
    if (f) {
             if (s) System.out.println("Некоректная позиция");
             continue;
           }
     
    sh[shn].setShip(c, (int)(ch-'a'), (int)(che-'a'), in-1, ine-1);
 }
    
    break;
  }while(true);
    int z;
    for (z=0;z<10;z++)
    if (sh[z].getFree())  { 
                            
                            break;
                          }
    if (z==10) b=false;
  }
   

  
}

static void Play (Ship[] shp,Cell[][] cp, Ship[] shc, Cell[][] cc,Scanner sc) throws Exception
{ int inr=0,c=0,ind=0;
  char chr=0,chd=0;
  boolean np=false,nc=false;
  do {
  drawP(shp,cp,shc,cc) ;
  String st,msg,msgc;
  int in;
  char ch;
  boolean b=false,be=false;
  try {
  System.out.print("Введите позицию: ");
  st=sc.nextLine();
  if (st.length()<2||st.length()>3) {
                                      System.out.println("Некоректная позиция");
                                      continue;
                                    }
  ch=st.charAt(0);
  if (ch<'a'||ch>'j')  {
                         System.out.println("Некоректная позиция");
                         continue;
                       }
  st=st.substring(1);
  in=Integer.parseInt(st);
 }
  catch (Exception e) {
   in=0;
   ch='z';
  }
   if (in<1||in>10) {
                      System.out.println("Некоректная позиция");
                      continue;
                    }
   if (cc[in-1][ch-'a'].checkCrash()) {
                                        System.out.println("Выстрел уже производился");
                                        continue;
                                      }
   else cc[in-1][ch-'a'].setCrash();
   if (cc[in-1][ch-'a'].getShip()==null) {
                                          msg="Вы: "+ch+in+" Мимо!";
                                          np=false;
                                         }
   else {
         msg="Вы: "+ch+in+" Попадание!";
         np=true;
        }
   if (cc[in-1][ch-'a'].getShip()!=null&&cc[in-1][ch-'a'].getShip().checkCrash()) {
                                                                                    cc[in-1][ch-'a'].getShip().setArea(cc);
                                                                                    msg="Вы: "+ch+in+" Убит!";
                                                                                    np=true;
                                                                                  }
   int i;
   for ( i=0;i<10;i++)
   {
     if (!shc[i].checkCrash()) break;
     if (i==9) {
     b=true;
     }
   }
   if (b) { 
            System.out.println(msg);
            drawP(shp,cp,shc,cc) ;
            System.out.println("Вы победили!");
            break;
          }
    
   if (np) { System.out.println(msg);
             continue;
           }
   do {
   switch (c)  {
   case 0: {
   do {
   ch=0;
   ch+=Math.random()*10;
   in=1;
   in+=Math.random()*10;
   if (cp[in-1][ch].checkCrash()) continue;
   else cp[in-1][ch].setCrash();
   if (cp[in-1][ch].getShip()==null) {
   msgc="Компьютер: "+(char)(ch+'a')+in+": Мимо!";
   c=0;
   nc=false;
   }
   else {
         msgc="Компьютер: "+(char)(ch+'a')+in+": Попадание!";
         c++;
         chr=ch;
         inr=in;
         nc=true;
        }
   if (cp[in-1][ch].getShip()!=null&&cp[in-1][ch].getShip().checkCrash()) {
                                                                            cp[in-1][ch].getShip().setArea(cp);
                                                                            msgc="Компьютер: "+(char)(ch+'a')+in+": Убит!";
                                                                            c=0;
                                                                            nc=true;
                                              
                                                                          }
   break;
   }while(true);
   break;
   }
   case 1: {
   do {
   ind=inr;
   chd=chr;
   double m=Math.random();
   if (m<0.25) chd++;
   else if (m<0.5) chd--;
        else if (m<0.75) ind++;
             else ind--;
   if (ind>10||ind<1||chd<0||chd>9) continue;
   if (cp[ind-1][chd].checkCrash()) continue;
   cp[ind-1][chd].setCrash();
   if (cp[ind-1][chd].getShip()==null) {
   msgc="Компьютер: "+(char)(chd+'a')+ind+": Мимо!";
   c=1;
   nc=false;
   }
   else {msgc="Компьютер: "+(char)(chd+'a')+ind+": Попадание!";
         c=2;
         nc=true;
        }
   if (cp[ind-1][chd].getShip()!=null&&cp[ind-1][chd].getShip().checkCrash()) {cp[ind-1][chd].getShip().setArea(cp);
                                                                               msgc="Компьютер: "+(char)(chd+'a')+ind+": Убит!";
                                                                               c=0;
                                                                               nc=true;
                                                                              }
   break;
   }while(true);
    break;
   }
   default: {
   if (chr<'a'||chd<'a') {
   chr+='a';
   chd+='a';
   }
   int tins,tine;
   char tchs,tche;
   if (inr==ind) {                        
                  tins=tine=inr;
                  if (chr>chd) {
                                 tche=chr;
                                 tchs=chd;
                                                                
                               }
                  else {
                         tche=chd;
                         tchs=chr;
                       }
                                                 
                                               }
   else { 
         tchs=tche=chr;
         if (inr>ind) {
                        tine=inr;
                        tins=ind;
                      }
         else {
                tine=ind;
                tins=inr;
              }
        }
   do {
       int pin;
       char pch;
       double r=Math.random();
       boolean f=true;
       if (r>0.5) {
                    if (tchs==tche) {pin=tine+1;
                                     pch=tche;
                                    }
                    else {pin=tine;
                          pch=tche;
                          pch++;
                         }
                  }
       else {
             if (tchs==tche) {pin=tins-1;
                              pch=tchs;
                             }
             else {
                   pin=tins;
                   pch=tchs;
                   pch--;
                  }
            }
       if (pin<1||pin>10) { 
                           continue;
                          }
       if (pch<'a'||pch>'j') { 
                               continue;
                             }
       if (cp[pin-1][pch-'a'].checkCrash()) { 
                                             continue;
                                            }
       cp[pin-1][pch-'a'].setCrash();
       if (cp[pin-1][pch-'a'].getShip()==null) {c=2;
                                                msgc="Компьютер: "+(char)pch+pin+": Мимо!";
                                                f=false;
                                                nc=false;
                                               }
       else {c++;
             msgc="Компьютер: "+(char)pch+pin+": Попадание!";
             nc=true;
            }
       if (cp[pin-1][pch-'a'].getShip()!=null&&cp[pin-1][pch-'a'].getShip().checkCrash()) {c=0;
                                                                                           msgc="Компьютер: "+(char)pch+pin+": Убит!";
                                                                                           cp[pin-1][pch-'a'].getShip().setArea(cp);
                                                                                           f=false;
                                                                                           nc=true;
                                                                                          }
       if (f) {
                if (pch==chr+1||pch==chr-1) chr=pch; 
                if (pch==chd+1||pch==chd-1) chd=pch;
                if (pin==inr+1||pin==inr-1) inr=pin;
                if (pin==ind+1||pin==ind-1) ind=pin;
              }
       break;
       }while(true);
        break;
       }
     }  
        if (nc) { Thread.sleep(2000);
        System.out.println(msgc);
        drawP(shp,cp,shc,cc) ;
        for (i=0;i<10;i++)
       {
         if (!shp[i].checkCrash()) break;
         if (i==9) {
                     be=true;
                   }
       }
        if(!be) continue;
      }
        break;
    }  while(true);
     for (i=0;i<10;i++)
    {
      if (!shp[i].checkCrash()) break;
      if (i==9) {
      be=true;
      }
    }
    if (be) {System.out.println(msg+"     "+msgc);
             drawP(shp,cp,shc,cc) ;
             System.out.println("Вы проиграли!");
             break;
            }
    System.out.println(msg+"     "+msgc);
   }while (true);
} 

   public static void main(String[] args) 
  { 
    Cell[][] cp=new Cell[10][10],cc=new Cell[10][10];
    Ship[] shp=new Ship[10],shc=new Ship[10];
    for (int i=0;i<10;i++) //инициализация клеток
    for (int j=0;j<10;j++)
   {
    cp[j][i]=new Cell(i,j+1);
    cc[j][i]=new Cell(i,j+1);
   }
    for (int i=0;i<10;i++) //инициализация кораблей
   {
     if (i==0) {
     shp[i]=new Ship(4,i);
     shc[i]=new Ship(4,i);
    }
     if (i==1||i==2) {
     shp[i]=new Ship(3,i);
     shc[i]=new Ship(3,i); 
     }
     if (i>=3&&i<=5) {
     shp[i]=new Ship(2,i);
     shc[i]=new Ship(2,i);  
     }
     if (i>=6&&i<=9) {
     shp[i]=new Ship(1,i);
     shc[i]=new Ship(1,i);   
     }
   }
     try (Scanner sc=new Scanner(System.in)) {
     Set(shp,cp,true,sc);
     drawS(shp,cp);
     Set(shc,cc,false,sc);
     try {
     Play(shp,cp,shc,cc,sc);
     }
     catch (Exception e) {
     System.out.println("Неизвестная ошибка");
     }
    }
   }
}
