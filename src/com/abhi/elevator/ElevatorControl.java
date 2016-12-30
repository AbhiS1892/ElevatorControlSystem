package com.abhi.elevator;

import java.io.IOException;
import java.util.Scanner;

public class ElevatorControl {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		ElevatorUp up = new ElevatorUp();
		ElevatorDown down = new ElevatorDown();

		int key, value;
		int[] arr = new int[10];

		for(int i=0; i<10; i++){
			arr[i] = sc.nextInt();
		}

		int peopleWaiting = sc.nextInt();

		for(int i = 0; i < peopleWaiting; i++) {
			key = Integer.parseInt(sc.next());
			value = Integer.parseInt(sc.next());

			if(key < value){
				up.populateUpElevator(key, value);
			}else if(key > value){
				down.populateDownElevator(key, value);
			}
		}

		int powerConsumed = sc.nextInt(); 
		int extra = sc.nextInt();
		sc.close();
		
		int[] upElevatorResult = new int[2];
		int[] downElevatorResult = new int[2];
		int finalOptimizedTime = 0, finalPowerConsumption = 0;

		if((up.upElevatorSize() != 0) && (down.downElevatorSize() != 0)){
			upElevatorResult = up.upElevatorConsumption(powerConsumed);
			downElevatorResult = down.downElevatorConsumption(powerConsumed);
			finalOptimizedTime = upElevatorResult[0] + downElevatorResult[0];
			finalPowerConsumption = upElevatorResult[1] + downElevatorResult[1];
		}else if(up.upElevatorSize() == 0){
			downElevatorResult = down.downElevatorConsumption(powerConsumed);
			finalOptimizedTime = downElevatorResult[0];
			finalPowerConsumption = downElevatorResult[1];
		}else{
			upElevatorResult = up.upElevatorConsumption(powerConsumed);
			finalOptimizedTime = upElevatorResult[0];
			finalPowerConsumption = upElevatorResult[1];
		}
		
		System.out.println("\n" + "OUTPUT:");
		System.out.println("Optimized Time : " + finalOptimizedTime );
		System.out.println("Power Consumed : " + finalPowerConsumption);

	}
}

