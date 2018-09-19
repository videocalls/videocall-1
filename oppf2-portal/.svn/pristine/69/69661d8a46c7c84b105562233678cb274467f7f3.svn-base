# Host: 10.10.10.100  (Version 5.7.17-ndb-7.5.5-cluster-gpl-log)
# Date: 2017-07-05 10:43:42
# Generator: MySQL-Front 5.4  (Build 4.71) - http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Event "adm_api_traffic_event_monthly"
#

DROP EVENT IF EXISTS `adm_api_traffic_event_monthly`;
CREATE EVENT `adm_api_traffic_event_monthly` ON SCHEDULE EVERY 1 MONTH STARTS '2016-01-01 04:05:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    /*
    * start : 현재 년 + 01월 01일 00 시 05 분
    * 매월 1일 정각 5분마다 데이터 체크하여 시간단위 traffic data를 집계한다.
    */
    DECLARE v_std_date varchar(10);       /* 기준 date */
    DECLARE v_data_cnt int;               /* 이전 일자 기준 데이터 체크 수*/
    
    set v_std_date = date_format(subdate(now(), INTERVAL 1 MONTH), '%Y%m');
    
    /**
    * 현재월의 이전월의 데이터가 집계되어있는지 여부 체크
    */
    select count(*)
    into v_data_cnt
    from adm_api_traffic_monthly
    where std_date = v_std_date COLLATE utf8_general_ci
    ;
    
if v_data_cnt <= 0 then     
    insert into adm_api_traffic_monthly(
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
    select  v_std_date as std_date,
            ifnull(a.api_id, '') as api_id,
            ifnull(a.app_id, '') as app_id,
            ifnull(a.app_key, '') as app_key,
            ifnull(a.api_group, '') as api_group,
            ifnull(a.api_service, '') as api_service,
            ifnull(a.response_result_yn, '') as response_result_yn,
            max(a.api_method) as api_method,
            max(a.api_resource) as api_resource,
            round(sum(a.sum_api_duration) / sum(a.cnt_api_duration), 3)     as avg_api_duration,
            max(a.max_api_duration)                                         as max_api_duration, 
            min(a.min_api_duration)                                         as min_api_duration,
            sum(a.sum_api_duration)                                         as sum_api_duration,
            sum(a.cnt_api_duration)                                         as cnt_api_duration,    
            round(sum(a.sum_sif_duration) / sum(a.cnt_sif_duration), 3)     as avg_sif_duration,
            max(a.max_sif_duration)                                         as max_sif_duration, 
            min(a.min_sif_duration)                                         as min_sif_duration,
            sum(a.sum_sif_duration)                                         as sum_sif_duration,
            sum(a.cnt_sif_duration)                                         as cnt_sif_duration,
            now()                                                           as create_date
    from adm_api_traffic_daily a               
    where a.std_date >= concat(v_std_date ,'00') COLLATE utf8_general_ci
    and   a.std_date <= concat(v_std_date ,'99') COLLATE utf8_general_ci
    group by a.api_id, a.app_id, a.app_key, a.api_group, a.response_result_yn   
    ;

end if;
END;
