# Host: 10.10.10.100  (Version 5.7.17-ndb-7.5.5-cluster-gpl-log)
# Date: 2017-07-05 10:43:35
# Generator: MySQL-Front 5.4  (Build 4.71) - http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Event "adm_api_traffic_event_min"
#

DROP EVENT IF EXISTS `adm_api_traffic_event_min`;
CREATE EVENT `adm_api_traffic_event_min` ON SCHEDULE EVERY 1 MINUTE STARTS '2016-09-09 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    /*
    * 개발기 서버별 시간이 틀려 30분 이전 데이터 집계
    *
    * 5분마다 데이터 체크하여 분단위 traffic data를 집계한다.
    */
    DECLARE v_std_date varchar(12) ;       /* 기준 date */
    DECLARE v_start_date varchar(20);     /* 기준 start date */
    DECLARE v_end_date varchar(20);       /* 기준 end date */
    
    DECLARE v_cur_min varchar(2);         /* 현재 분 */
    DECLARE v_data_cnt int;               /* 현재 시간 기준 데이터 체크 수*/
    
    set v_std_date = date_format(subdate(now(), INTERVAL 5 MINUTE), '%Y%m%d%H%i');
    set v_start_date = date_format(subdate(now(), INTERVAL 5 MINUTE), '%Y-%m-%d %H:%i:00');
    set v_end_date = date_format(subdate(now(), INTERVAL 1 MINUTE), '%Y-%m-%d %H:%i:59');
    
    set v_cur_min = date_format(now(), '%i');
    
    /* 분(5분마다) */
    if 
        v_cur_min = '00' or 
        v_cur_min = '05' or 
        v_cur_min = '10' or 
        v_cur_min = '15' or 
        v_cur_min = '20' or 
        v_cur_min = '25' or 
        v_cur_min = '30' or 
        v_cur_min = '35' or 
        v_cur_min = '40' or 
        v_cur_min = '45' or 
        v_cur_min = '50' or 
        v_cur_min = '55'
    then        
        /*
        * 해당 시간의 -5 ~ -1 까지의 시간을 체크한다.
        * 05 : 00 ~ 04					-> 00
        * 10 : 05 ~ 09					-> 05
        * 15 : 10 ~ 14					-> 10
        * 20 : 15 ~ 19					-> 15
        * 25 : 20 ~ 24					-> 20
        * 30 : 25 ~ 29					-> 25
        * 35 : 30 ~ 34					-> 30
        * 40 : 35 ~ 39					-> 35
        * 45 : 40 ~ 44					-> 40
        * 50 : 45 ~ 49					-> 45
        * 55 : 50 ~ 54					-> 50
        * 00 : 55 ~ 59 (이전시간)		-> 55
        */
        select count(*)
        into v_data_cnt
        from adm_api_traffic_min
        where std_date = v_std_date COLLATE utf8_unicode_ci
        ;
        
        /* 저장 데이터가 없을 경우에만 셋팅한다. */
        if v_data_cnt <= 0 then     
            insert into adm_api_traffic_min(
                std_date,
                api_id,
                app_id,
                app_key,
                api_group,
                api_service,
                response_result_yn,
                api_method,
                api_resource,
                avg_api_duration,
                max_api_duration,
                min_api_duration,
                sum_api_duration,
                cnt_api_duration,
                avg_sif_duration,
                max_sif_duration,
                min_sif_duration,
                sum_sif_duration,
                cnt_sif_duration,
                create_date
            )
            select  max(std_date) as std_date,
                    ifnull(a.api_id, '') as api_id,
                    ifnull(a.app_id, '') as app_id,
                    ifnull(a.app_key, '') as app_key,
                    ifnull(a.api_group, '') as api_group,
                    ifnull(a.api_service, '') as api_service,
                    ifnull(a.response_result_yn, '') as response_result_yn,
                    max(a.api_method) as api_method,
                    max(a.api_resource) as api_resource,
                    round(sum(a.api_service_duration) / count(*), 3)    as avg_api_duration,
                    max(a.api_service_duration)                         as max_api_duration, 
                    min(a.api_service_duration)                         as min_api_duration,
                    sum(a.api_service_duration)                         as sum_api_duration,
                    count(*)                                            as cnt_api_duration,
                    round(sum(a.sif_service_duration) / count(*), 3)    as avg_sif_duration,
                    max(a.sif_service_duration)                         as max_sif_duration, 
                    min(a.sif_service_duration)                         as min_sif_duration,
                    sum(a.sif_service_duration)                         as sum_sif_duration,
                    count(*)                                            as cnt_sif_duration,
                    now()                                               as create_date
            from(
                select  
                        v_std_date as std_date,
                        a.api_id,
                        b.app_id,
                        a.app_key,
                        a.api_group,
                        a.api_service,
                        a.api_method,
                        a.api_resource,
                        a.response_result_yn,
                        a.api_service_duration,
                        a.sif_service_duration
                from ca_gw_api_traffic_view a
                left join com_app_view b on a.app_key = b.app_key
                where api_response_time >= v_start_date 
                and   api_response_time <= v_end_date
            ) a                
        group by a.api_id, a.app_id, a.app_key, a.api_group, a.response_result_yn                     
            ;
		end if;
    end if;
END;
