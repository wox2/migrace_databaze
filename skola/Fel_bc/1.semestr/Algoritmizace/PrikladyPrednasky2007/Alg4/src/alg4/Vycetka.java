package alg4;

import java.util.*;
public class Vycetka {
  public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int plat, platX;
int p5000,p1000,p500,p100;
    platX=sc.nextInt();
    plat=platX;
    p5000=plat/5000;
    plat=plat%5000;
    p1000=plat/1000;
    plat=plat%1000;
    p500=plat/500;
    plat=plat%500;
    p100=plat/100;
    plat=plat%100;
    System.out.print("Vyplata castky " + platX + " je:\n" 
     + p5000 +   ",\n" 
     + p1000 +   ",\n"
     + p500  +   ",\n" 
     + p100  +   ", \na zbytek " + plat);
  }}

