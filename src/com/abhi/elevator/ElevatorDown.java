package com.abhi.elevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ElevatorDown {
	Map<Integer, TreeSet<Integer>> downMap = new TreeMap<Integer, TreeSet<Integer>>();
	int maxLength = 0, maxElement = 0, powerDiff = 0, optimizedTime = 0, minElement = 0;
	int[] result = new int[2];

	public void populateDownElevator(int key, int value){
		if(downMap.containsKey(key)){
			TreeSet<Integer> tempArray = downMap.get(key);
			tempArray.add(value);
			downMap.put(key, tempArray);
		}else{
			TreeSet<Integer> tempArray = new TreeSet<>();
			tempArray.add(value);
			downMap.put(key, tempArray);
		}
	}

	public int downElevatorSize(){
		return downMap.size();
	}

	public int[] downElevatorConsumption(int powerConsumed){

		Set<Integer> keySet = downMap.keySet();

		Integer[] array = keySet.toArray(new Integer[keySet.size()]);

		Collections.reverse(Arrays.asList(array));

		if(array.length == 1){
			maxElement = array[0];
			maxLength = downMap.get(array[0]).size();

			if(maxElement == maxLength){
				optimizedTime = (maxElement * 2) + 1;
				powerConsumed = powerConsumed + (20 * maxElement);
			}else{
				optimizedTime = maxElement + maxLength + 1;
				powerDiff = maxElement - maxLength;
				powerConsumed = powerConsumed + (20 * maxLength) + (10 * powerDiff);
			}

			result[0] = optimizedTime;
			result[1] = powerConsumed;
			return result;
		}else{
			for(Integer m : keySet){
				int tempMin = Collections.min(downMap.get(m));
				if(tempMin == minElement){
					maxLength = array[0] + 1;
				}else{
					maxLength = array[0] - tempMin;
				}
			}

			maxElement = array[0];	
			for(int k = 1; k < array.length; k++){
				if(!downMap.get(array[0]).contains(array[k])){

					TreeSet<Integer> res = downMap.get(array[k]);
					Iterator<Integer> i = res.iterator();

					while(i.hasNext()){
						int num = i.next();
						if((downMap.get(array[0]).contains(num))){				
							maxLength -= 1;
						}
					}
				}
			}

		}


		optimizedTime = maxElement + maxLength + 1;
		powerDiff = maxElement - maxLength;
		powerConsumed = powerConsumed + (20 * maxLength) + (10 * powerDiff);

		result[0] = optimizedTime;
		result[1] = powerConsumed;
		return result;
	}
}