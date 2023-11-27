package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //금액 입력 ( 1000원 단위)
        System.out.println("구입금액을 입력해 주세요.");
        int bill = Integer.parseInt(br.readLine());
        while((bill%1000)!=0){
            System.out.println("구입금액을 다시 입력해 주세요.");
            bill = Integer.parseInt(br.readLine());
        }


        // 당첨번호 입력
        System.out.println("당첨 번호를 입력해 주세요.");
        String[] lottoNum_S = br.readLine().split(","); //String으로 입력받은 로또번호
//        if(lottoNum_S.length!=6){
//            lottoNum_S = br.readLine().split(",");
//        }

        List<Integer> numbers = new ArrayList<Integer>();  //Integer 로또 번호를 저장할 List객체
        for(int i=0; i<lottoNum_S.length; i++){
           numbers.add(Integer.parseInt(lottoNum_S[i]));
        }
        Lotto lotto = new Lotto(numbers);

        //보너스 번호 입력
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonus = Integer.parseInt(br.readLine());

        //로또 출력
        System.out.println((bill/1000)+"개를 구매했습니다.");
        List<Integer>[] my_lotto  = new List[bill/1000];   //발급 받은 로또 번호 리스트
        for(int i=0; i<(bill/1000); i++){
            List<Integer> lotto_numbers = lotto.output();
            System.out.println(lotto_numbers);
            my_lotto[i] = lotto_numbers;
        }

        //결과 확인
        lotto.check(my_lotto,bill,bonus);


//        "3개 일치 (5,000원) - 1개",
//        "4개 일치 (50,000원) - 0개",
//        "5개 일치 (1,500,000원) - 0개",
//        "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
//        "6개 일치 (2,000,000,000원) - 0개",
//        "총 수익률은 62.5%입니다."
    }
}
