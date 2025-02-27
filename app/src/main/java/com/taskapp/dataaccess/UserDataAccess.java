package com.taskapp.dataaccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.taskapp.model.User;

public class UserDataAccess {
    private final String filePath;

    public UserDataAccess() {
        filePath = "app/src/main/resources/users.csv";
    }

    /**
     * 自動採点用に必要なコンストラクタのため、皆さんはこのコンストラクタを利用・削除はしないでください
     * @param filePath
     */
    public UserDataAccess(String filePath) {
        this.filePath = filePath;
    }

    /**
     * メールアドレスとパスワードを基にユーザーデータを探します。
     * @param email メールアドレス
     * @param password パスワード
     * @return 見つかったユーザー
     */
    public User findByEmailAndPassword(String email, String password) {
        //初期値でnullを入れる。条件に合わない場合nullを返す
        User user = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] userparts = line.split(",");
                //csvの2.3番目の要素と一致しない場合スキップ
                if(!(userparts[2].equals(email) && userparts[3].equals(password))) continue;
                int Code = Integer.parseInt(userparts[0]);
                String name = userparts[1];
                String Email = userparts[2];
                String Password = userparts[3];
                //user変数にマッピング
                user = new User(Code, name, Email, Password);

            }
        } catch (IOException e) {
            e.printStackTrace();;
        }
        return user;
    }

    /**
     * コードを基にユーザーデータを取得します。
     * @param code 取得するユーザーのコード
     * @return 見つかったユーザー
     */
    public User findByCode(int code) {
        User user = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] userparts = line.split(",");
                int Code = Integer.parseInt(userparts[0]);
                if(code != Code) continue;
                String name = userparts[1];
                String Email = userparts[2];
                String Password = userparts[3];
                user = new User(Code, name, Email, Password);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user ;
    }
}
