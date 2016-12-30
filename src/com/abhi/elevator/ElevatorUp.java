package com.abhi.elevator;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ElevatorUp {
	Map<Integer, TreeSet<Integer>> upMap = new TreeMap<Integer, TreeSet<Integer>>();
	int maxLength = 0, maxElement = 0, powerDiff = 0, optimizedTime = 0;
	int[] result = new int[2];

	public void populateUpElevator(int key, int value){
		if(upMap.containsKey(key)){
			TreeSet<Integer> tempArray = upMap.get(key);
			tempArray.add(value);
			upMap.put(key, tempArray);
		}else{
			TreeSet<Integer> tempArray = new TreeSet<>();
			tempArray.add(value);
			upMap.put(key, tempArray);
		}
	}
	
	public int upElevatorSize(){
		return upMap.size();
	}

	public int[] upElevatorConsumption(int powerConsumed){

		Set<Integer> keySet = upMap.keySet();
		Integer[] array = keySet.toArray(new Integer[keySet.size()]);

		if(array.length == 1){
			maxElement = Collections.max(upMap.get(array[0]));
			maxLength = upMap.get(array[0]).size();

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
				int tempMax = Collections.max(upMap.get(m));
				if(maxElement  < tempMax){
					maxElement = tempMax;
				}
			}

			maxLength = maxElement;
			for(int k = 1; k < array.length; k++){
				if(upMap.get(array[0]).contains(array[k])){
					TreeSet<Integer> res = upMap.get(array[k]);

					Iterator<Integer> i = res.iterator();
					while(i.hasNext()){
						int temp = Collections.max(upMap.get(array[k]));
						int num = i.next();
						if(!(upMap.get(array[0]).contains(num)) && num < temp){				
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