package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
        System.out.println("로또번호 = "+ numbers);
    }

    // 당첨번호 6개인지 확인
    private void validate(List<Integer> numbers)  {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또번호 6자리가 아닙니다");

        }
    }

    //로또번호 발행
    public List<Integer> output(){
        List<Integer> lotto_numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(lotto_numbers);
        return lotto_numbers;
    }

    //로또 당첨 확인
    public void check(List<Integer>[] my_lotto,int bill){
        int[] result = new int[my_lotto.length];
        for(int i=0; i<my_lotto.length;i++){
            result[i] = check2(my_lotto[i]);
        }
        int money = count_result(result);
        percent(money, bill);

    }
    //로또 당첨 확인 2
    private int check2(List<Integer> check){
        int cnt = 0;
        for(int num : this.numbers){
          if(check.contains(num)){
              cnt++;
          }
        }
        return cnt;
    }

    //당첨 정리
    private int count_result(int[] result){
        HashMap<Integer, Integer> cnt_map = new HashMap<Integer,Integer>();
        cnt_map.put(3,0);
        cnt_map.put(4,0);
        cnt_map.put(5,0);
        cnt_map.put(6,0);
        for(int cnt: result){
            if(cnt > 2){
                cnt_map.put(cnt,cnt_map.get(cnt)+1);;
            }
        }

        System.out.println("3개 일치 (5,000원) - "+cnt_map.get(3)+"개");
        System.out.println("4개 일치 (50,000원) - "+cnt_map.get(4)+"개");
        System.out.println("5개 일치 (1,500,000원) - "+cnt_map.get(5)+"개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+cnt_map.get(5)+"개");
        System.out.println("6개 일치 (2,000,000,000원) - "+cnt_map.get(6)+"개");

        int money = cnt_map.get(3)*5000+cnt_map.get(4)*50000+cnt_map.get(5)*1500000+cnt_map.get(6)*200000000;
        return  money;
    }
    private void percent(int money, int bill){
        double percent = (double) money/(double) bill *100;
        System.out.println("총 수익률은 "+ String.format("%.2f",percent) +"%입니다.");
    }

}
