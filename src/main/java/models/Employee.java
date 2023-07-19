package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 従業員データのDTOモデル ENTITY_EMP = "employee";
 *
 */
@Table(name = JpaConst.TABLE_EMP)
@NamedQueries({
  //全ての従業員をidの降順に取得する
    @NamedQuery(
            name = JpaConst.Q_EMP_GET_ALL,
            query = JpaConst.Q_EMP_GET_ALL_DEF),//"SELECT e FROM Employee AS e ORDER BY e.id DESC";
  //全ての従業員の件数を取得する
    @NamedQuery(
            name = JpaConst.Q_EMP_COUNT,
            query = JpaConst.Q_EMP_COUNT_DEF),//"SELECT COUNT(e) FROM Employee AS e";
  //指定した社員番号を保持する従業員の件数を取得する
    @NamedQuery(
            name = JpaConst.Q_EMP_COUNT_REGISTERED_BY_CODE,
            query = JpaConst.Q_EMP_COUNT_REGISTERED_BY_CODE_DEF),//"SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;
  //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    @NamedQuery(
            name = JpaConst.Q_EMP_GET_BY_CODE_AND_PASS,
            query = JpaConst.Q_EMP_GET_BY_CODE_AND_PASS_DEF)//"SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
})

@Getter//全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter//全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor//引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor//全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Employee {

    /**
     * id EMP_COL_ID = "id";
     */
    @Id
    @Column(name = JpaConst.EMP_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 社員番号 EMP_COL_CODE = "code";
     */
    @Column(name = JpaConst.EMP_COL_CODE, nullable = false, unique = true)
    private String code;

    /**
     * 氏名 EMP_COL_NAME = "name";
     */
    @Column(name = JpaConst.EMP_COL_NAME, nullable = false)
    private String name;

    /**
     * パスワード EMP_COL_PASS = "password";
     */
    @Column(name = JpaConst.EMP_COL_PASS, length = 64, nullable = false)
    private String password;

    /**
     * 管理者権限があるかどうか（一般：0、管理者：1）EMP_COL_ADMIN_FLAG = "admin_flag";
     */
    @Column(name = JpaConst.EMP_COL_ADMIN_FLAG, nullable = false)
    private Integer adminFlag;

    /**
     *登録日時 EMP_COL_CREATED_AT = "created_at";
     */
    @Column(name = JpaConst.EMP_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時 EMP_COL_UPDATED_AT = "updated_at";
     */
    @Column(name = JpaConst.EMP_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）EMP_COL_DELETE_FLAG = "delete_flag";
     */
    @Column(name = JpaConst.EMP_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;
}
