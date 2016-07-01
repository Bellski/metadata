/*

import ru.bellski.metadata.examples.domain.UserSqlMetadata;
import java.lang.String;
import java.lang.Boolean;

metaQuery {

    sqlMetadata = UserSqlMetadata;

    parameters = [
        String s,
        Boolean a
    ];

    return UserSqlMetadata;
}

*/

-- Получение данных дела
-- Параметры:
WITH params AS ( SELECT
                   ? :: BIGINT AS company_id --   1. id компании логина (для получения номера дела),
                   ? :: BIGINT AS claim_id   --   2. id дела (CAS1)
),
    claim_stuff AS (
      SELECT
        claim.id                                           AS claimId,
        CAST(cas1.from_unixtime(claim.claim_date) AS DATE) AS claimDate,
        ins_type.nameName                                      AS insuranceType,
        ins_risk.nameName                                      AS insuranceRisk,
        claim_no_by_company.claim_no,
        claim_no_by_company.accident_no,
        car.znak                                           AS "address.country",
        car.vipusk,
        car.vin,
        car.dvig_no,
        car.shassi_no,
        car.kuz_no,
        car.power,
        car.reg,
        car.pts,
        car.custom,
        car.run,
        car.color_kuz,
        car.capacity,
        car.cat,
        car.place_after_crash,
        car.spec_rec,
        car.dvig_model,
        car.dvig_type,
        car.country,
        car.kuz_type,
        car.drv_type,
        car.disks_type,
        car.doors_count,
        car.places_count,
        car.tyres_mark,
        car.ts_entry_date,
        car.defected_insured,
        car.defects_not_claim,
        car.defected_insured_descr,
        car.defects_not_claim_descr,
        car.first_registration_date,
        person_fiz.id                                      AS fiz_id,
        person_fiz.passport_ser,
        person_fiz.passport_no,
        person_fiz.passport_vidan,
        person_fiz.zip,
        person_fiz.region,
        person_fiz.district,
        person_fiz.city,
        person_fiz.street,
        person_fiz.house,
        person_fiz.corp,
        person_fiz.flat,
        person_fiz.phone,
        person_fiz.l_name,
        person_fiz.f_name,
        person_fiz.m_name,
        person_fiz.born_place,
        person_fiz.born_date,
        person_fiz.dov_no,
        person_fiz.other_doc,
        person_fiz.permis,
        person_fiz.inn,
        person_fiz.rs,
        person_fiz.ls,
        person_fiz.bank_name,
        person_fiz.bank_bik,
        person_fiz.bank_ks,
        person_fiz.kpp,
        person_fiz.email,
        person_fiz.bank_location,
        person_fiz.addr_kladr_code,
        person_fiz.country_reg,
        person_fiz.type_comm_id,
        person_fiz.address_id,
        CASE car.custom
        WHEN NULL
          THEN ARRAY [NULL :: TEXT, NULL :: TEXT, NULL :: TEXT, NULL :: TEXT]
        WHEN 'undef'
          THEN ARRAY [NULL :: TEXT, NULL :: TEXT, NULL :: TEXT, NULL :: TEXT]
        WHEN 'concern'
          THEN (
            SELECT ARRAY [concern, model, NULL :: TEXT, ts_type.title]
            FROM cas1.car_custom_concern
              JOIN cas1.ts_type ON ts_type.id = car_custom_concern.type_id
            WHERE car_id = car.id
          )
        WHEN 'model'
          THEN (
            SELECT ARRAY [ts_concern.title, model, NULL :: TEXT, ts_type.title]
            FROM cas1.car_custom_model
              JOIN cas1.ts_concern ON ts_concern.id = car_custom_model.concern_id
              JOIN cas1.ts_type ON ts_type.id = car_custom_model.type_id
            WHERE car_id = car.id
          )
        WHEN 'modif'
          THEN (
            SELECT ARRAY [ts_concern.title, ts_model.title, car_custom_modif.modif, ts_type.title]
            FROM cas1.car_custom_modif
              JOIN cas1.ts_model ON ts_model.id = car_custom_modif.model_id
              JOIN cas1.ts_concern ON ts_concern.id = ts_model.concern_id
              JOIN cas1.ts_type ON ts_type.id = ts_model.type_id
            WHERE car_id = car.id
          )
        WHEN 'none'
          THEN (
            SELECT ARRAY [ts_concern.title, ts_model.title, ts_modif.title, ts_type.title]
            FROM cas1.car_modif
              JOIN cas1.ts_modif ON ts_modif.id = car_modif.modif_id
              JOIN cas1.ts_model ON ts_model.id = ts_modif.model_id
              JOIN cas1.ts_concern ON ts_concern.id = ts_model.concern_id
              JOIN cas1.ts_type ON ts_type.id = ts_model.type_id
            WHERE car_id = car.id
          )
        END                                                AS car_model_array,
        person_jur.id                                      AS jur_id,
        person_jur.ownership                               AS jur_ownership,
        person_jur.jur_name                                AS jur_name,
        person_jur.addr_jur                                AS jur_addr_jur,
        person_jur.addr_post                               AS jur_addr_post,
        person_jur.inn                                     AS jur_inn,
        person_jur.rs                                      AS jur_rs,
        person_jur.bank_name                               AS jur_bank_name,
        person_jur.bank_ks                                 AS jur_bank_ks,
        person_jur.bank_bik                                AS jur_bank_bik,
        person_jur.kpp                                     AS jur_kpp,
        person_jur.okpo                                    AS jur_okpo,
        person_jur.oknh                                    AS jur_oknh,
        person_jur.head_pos                                AS jur_head_pos,
        person_jur.head_fio                                AS jur_head_fio,
        person_jur.corp_phone                              AS jur_corp_phone,
        person_jur.fax                                     AS jur_fax,
        person_jur.bank_location                           AS jur_bank_location,
        person_jur.addr_jur_kladr_code                     AS jur_addr_jur_kladr_code,
        person_jur.addr_post_kladr_code                    AS jur_addr_post_kladr_code,
        person_jur.jur_country                             AS jur_country,
        person_jur.jur_email                               AS jur_email,
        person_jur.jur_type_comm_id                        AS jur_type_comm_id,
        person_jur.jur_address_id                          AS jur_address_id,
        person_jur.post_address_id                         AS jur_post_address_id
      FROM cas1.claim
        CROSS JOIN params
        JOIN cas1.bp_choice ON bp_choice.id = claim.choice_id
        LEFT JOIN cas1.ins_type ON ins_type.id = bp_choice.ins_type_id
        LEFT JOIN cas1.ins_risk ON ins_risk.id = bp_choice.ins_risk_id
        LEFT JOIN cas1.claim_actual_data ON claim_actual_data.claim_id = claim.id
        LEFT JOIN cas1.car ON car.id = claim_actual_data.owner_car_id
        LEFT JOIN cas1.person ON person.id = claim_actual_data.owner_person_id
        LEFT JOIN cas1.person_fiz ON person_fiz.id = person.person_fiz_id
        LEFT JOIN cas1.person_jur ON person_jur.id = person.person_jur_id
        LEFT JOIN cas1.claim_no_by_company ON claim_no_by_company.version = COALESCE(
            (
              SELECT claim_no_by_company.version
              FROM cas1.claim_no_by_company
              WHERE
                claim_no_by_company.claim_id = claim.id
                AND claim_no_by_company.actual = 1
                AND claim_no_by_company.company_id = (
                  SELECT office.company_id
                  FROM cas1.bp_choice
                    JOIN cas1.office ON office.id = bp_choice.office_id
                  WHERE bp_choice.id = claim.choice_id
                )
            ),
            (
              SELECT claim_no_by_company.version
              FROM cas1.claim_no_by_company
              WHERE
                claim_no_by_company.claim_id = claim.id
                AND claim_no_by_company.actual = 1
                AND claim_no_by_company.company_id = params.company_id
            )
        )

      WHERE claim.id = params.claim_id
  )
SELECT
  claim_stuff.*,
  car_model_array [1] AS ts_concern,
  car_model_array [2] AS ts_model,
  car_model_array [3] AS ts_modif,
  car_model_array [4
  ]                   AS ts_type --todo-vs проверить, правильно ли ts_type берется? добавил, потому что не работал setVehicleType (Cas1DbManager.java:521, The column nameName ts_type was not found in this ResultSet.)
-- ,car_model_array[1]||'-'||car_model_array[2]||' '||car_model_array[3] AS car_model
FROM claim_stuff
