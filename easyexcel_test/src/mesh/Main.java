package mesh;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/6 2:39 PM
 * description
 */
public class Main {

    private static final Map<String, String> appcodeOwner = Maps.newHashMap();
    private static final Map<String, String> appcodeThirdBu = Maps.newHashMap();
    private static final Map<String, String> appcodeForthBu = Maps.newHashMap();
    private static final Map<String, String> ownerForthBu = Maps.newHashMap();
    private static final Map<String, String> appcodeLevel = Maps.newHashMap();
    private static final Map<String, String> appcodeJdk = Maps.newHashMap();

    private static final String aliveAppCodes10 = "h_hspa,mp_piao,f_x_sargeras,f_octopus_qmall,h_venus,cm_csp_console,h_crawl_scheduler,t_guard_order,vs_tts,cm_xue_rong_rong,cm_breakdown_practice,t_sophia_order,vs_fh_product_service,t_desert_feed,f_jy_charles,vc_dapp,h_datacube_wrapper,cm_test_producer,h_qta_prmt,ad_dapp_template,h_booking_app,sec_datasec,f_nflagship_provider,m_guess_u_like,f_flight_pid_pidsharedubbo,f_flight_akihabara,vs_b2c_front,h_n_callcenter,f_b2b_self_refund_ticket,f_checkin_crawl,f_hermes,m_hotelrank_search,h_uranus,f_apollo_x,h_supplier_orderAdapter,f_b2b_self_operate,t_train_bridge,h_xsearch,qta_order_core_p,t_guard_search,h_spa,h_hs_sirius,h_datacube,f_pangolin_search_post,f_tts_trade_core,pf_noah_web_ui,t_train_goods,f_athena_supercell,f_twell_domestic,f_fuwu_orderSite,t_guard_money,f_fuwu_data_jeus,p_stability,f_refund_core,f_fuwu_candlelight_qes,h_home_suggestion,f_athena_domestic_search,p_payServer,pf_algo_image_analysis_j,t_train_snow,f_qmall_product_core,h_prmt_supplier_core,h_ds_product_p,h_feedstream_qulang,pf_data_assets,f_qmall_refund,f_soraka,h_tianj_analysis,f_fare_inter,h_spa_intl,f_athena_domestic_tts,vs_vacation_api,h_order_horus_intl,t_sophia_money,f_inter_autotest_dispatch,h_qta_agent_core,f_twell_domestic_mstation,cm_pmo_project,sec_matrix,f_twell_rt_server,f_athena_gateway,p_gateway,f_self_platform,h_xsearch_data_sync,f_tts_core,f_tgq_center,mkt_data_management,h_galaxy_intl,t_sophia_supplier,piao_pagent,t_feed_crawler,p_mobile_payserver,qa_qodin,h_ims_provider,f_fuwu_qosm,f_fuwu_reimburse_core,vs_c2b_front,cm_csp_marshal,vs_c2b_app,cm_jiradev,cc_online_complain,t_train_am,vs_freecombine_touch,tc_advanced_tech,h_magic_service,f_tts_ticketdb,mp_price,pf_aced,sec_firewall,f_libra,f_fuwu_gq,cm_ceres,f_tts_flightchangecore,t_where_go,f_inter_twell,f_attack_damage_carry,h_datacube_parse,t_train_octopus,pp_u_qprivilege,bus_booking_core,h_tianj_es_qes,t_query_order,f_tts_activity_config,f_ttsi_inter_site,f_tts_core_plus,h_hulk_server,f_nflagship_ticket,f_tts_riskcontrol_provider,f_apollo_y,teller,h_qta_order_confirm_newprovider,f_athena_order,f_qmall_insurance,f_radar_analyse,f_tts_trade_booking,h_tamias_davinci,h_sargeras,vs_freebackend,f_ibeplus_provider,tc_qtrace_web,f_eva,bus_recommend,h_n_callcenter_qschedule,h_qta_baseinfo_provider,f_itts_worker_uniq,f_jy_charles_collector,h_qta_data_store_provider,f_qfuwu,f_b2b_self_change_ticket,p_qmp_nami_nexus,f_fare_report_analyze,f_abtest_statistic,t_guard_activity,pp_adam,f_tts_activity_small,f_transfer_provider,f_twell_rt_server_core,f_tts_activity_strategy,f_qmall_product,f_athena_pallas,f_todolist,f_fuwu_coreserv,c_feed_task,f_qbd_admin_server,f_white_screen,cm_csp_data_center";
    private static final Set<String> aliveAppCodeList10;
    private static final String aliveAppCodes5 = "ad_dapp_oss_admin,ad_dapp_template,b_wechatcenter,bus_booking_core,bus_common,bus_order_operate,bus_recommend,bus_xconfig,c_feed_task,c_feedstream_product,c_feedstream_qulang_index,c_product,c_qulang_rank,cc_online_complain,cm_breakdown_practice,cm_ceres,cm_cm_test_myorder,cm_coverage_portal,cm_csp_agent,cm_csp_console,cm_csp_data_center,cm_csp_marshal,cm_hotfix_console,cm_jiradev,cm_noah_holmes,cm_noah_lestrade,cm_noah_router,cm_noah_watson,cm_pmo_analysis,cm_pmo_project,cm_test_producer,cm_xue_rong_rong,dmf_ad_peri,f_abtest_statistic,f_apollo,f_apollo_x,f_apollo_y,f_athena_domestic_search,f_athena_domestic_tts,f_athena_gateway,f_athena_house,f_athena_order,f_athena_pallas,f_athena_search,f_athena_supercell,f_attack_damage_carry,f_b2b_self_change_ticket,f_b2b_self_operate,f_b2b_self_operate_policy,f_b2b_self_refund_ticket,f_cc_seat_manager,f_checkin_crawl,f_checkin_salad,f_data_qlibra_dataweb,f_data_qrocket_pushserver_offline,f_distributor_direct,f_eva,f_fare_inter,f_fare_report_analyze,f_farecore_provider,f_farecore_storage,f_fbi_flight_info,f_flagship_main,f_flight_akihabara,f_flight_pid_pidsharedubbo,f_fuwu_audiebant_event_core,f_fuwu_candlelight_qes,f_fuwu_coreserv,f_fuwu_data_jeus,f_fuwu_dialog,f_fuwu_gongdan,f_fuwu_gq,f_fuwu_kbms,f_fuwu_order_admin,f_fuwu_order_dispatch,f_fuwu_order_site,f_fuwu_orderapp,f_fuwu_ordercenter,f_fuwu_orderSite,f_fuwu_phoenix,f_fuwu_qosm,f_fuwu_refund,f_fuwu_reimburse_core,f_fuwu_watcher_platform,f_grand_line_adapter,f_haders_linker,f_hawk_eye_receiver,f_hermes,f_hotdog_athena,f_ibeplus_provider,f_iflagship_config,f_inter_autotest_dispatch,f_inter_gds,f_inter_twell,f_inter_twell_rt,f_iprovider_config,f_iprovider_config_search,f_itts_worker_uniq,f_jy_charles,f_jy_charles_collector,f_jy_charles_report,f_jy_prism,f_libra,f_lp_search,f_mossad_plus,f_ndc_platform,f_nflagship_provider,f_nflagship_ticket,f_nts_inter_pikachu,f_nts_inter_transaction,f_octopus_qmall,f_pangolin_search_compare,f_pangolin_search_post,f_pangolin_search_post_booking,f_pangolin_search_post_traffic,f_pangolin_search_post_ttsibooking,f_pid_manager,f_pid_net,f_pifa_distributor,f_poseidon_classic,f_poseidon_obs,f_poseidon_superman,f_qbd_admin_server,f_qbd_provider_server,f_qfuwu,f_qmall_insurance,f_qmall_product,f_qmall_product_core,f_qmall_refund,f_radar_analyse,f_radar_pull,f_refund_core,f_self_agent_fee,f_self_agent_platform,f_self_platform,f_share_platform_push,f_skynet,f_smartflight_server,f_soraka,f_tgq_center,f_todolist,f_transfer_provider,f_tts_activity_config,f_tts_activity_small,f_tts_activity_strategy,f_tts_afare,f_tts_afare_booking,f_tts_core,f_tts_core_plus,f_tts_flight_bonus,f_tts_flightchangecore,f_tts_inter_java,f_tts_package,f_tts_package_booking,f_tts_policy_provider,f_tts_policy_ui,f_tts_policy_ui_import,f_tts_riskcontrol_provider,f_tts_search,f_tts_search_booking,f_tts_tgq_rule,f_tts_ticketdb,f_tts_trade_booking,f_tts_trade_core,f_tts_trade_order,f_tts_user_labels,f_ttsi_inter_site,f_ttsi_worker_afare,f_twell_domestic,f_twell_domestic_mstation,f_twell_round_wbd,f_twell_rt_server,f_twell_rt_server_core,f_white_screen,f_wuh_workbench,f_x_sargeras,flight_supply_open_api,ft_entry,ft_qiousapi,h_booking_app,h_crawl_scheduler,h_datacube,h_datacube_inter,h_datacube_parse,h_datacube_wrapper,h_ds_product_p,h_feedstream_hotel,h_feedstream_qulang,h_galaxy_intl,h_goagain_product_provider,h_goagain_service_control,h_home_suggestion,h_hotelrank_search_intl,h_hs_sirius,h_hspa,h_hulk_server,h_ims_provider,h_magic_service,h_magicmirror,h_n_callcenter,h_n_callcenter_qschedule,h_naga_intl,h_order_horus_intl,h_order_pharos_intl,h_price_peach_intl,h_prmt_supplier_core,h_qhotel_hotel_info,h_qhotel_mars,h_qhotel_room_info,h_qta_agent_core,h_qta_agent_price,h_qta_baseinfo_provider,h_qta_data_store_provider,h_qta_order_confirm_newprovider,h_qta_parent_order,h_qta_prmt,h_qta_prmt_qtaman,h_sargeras,h_saturn,h_scout_job,h_spa,h_spa_intl,h_supplier_orderAdapter,h_tamias_davinci,h_tianj_analysis,h_tianj_es_qes,h_tiga_intl,h_torch,h_travelco,h_ugc_review_comment_cn,h_uranus,h_venus,h_xsearch,h_xsearch_data_sync,in_bnb_service,in_fantastic4,in_market_ad,in_rta_platform,in_thor,m_anti_craw_p,m_car_qb_biz,m_guess_u_like,m_hhotdog,m_hotel_hades,m_hotelrank_search,m_marmot_integralwall,mkt_data_management,mp_piao,mp_price,mp_price_control,mp_product,mp_tts2,p_accnt_export,p_gateway,p_mobile_payserver,p_payServer,p_pc_cashier,p_qmp_https,p_qmp_nami_nexus,p_stability,pf_aced,pf_algo_image_analysis_j,pf_data_assets,pf_dw_management,pf_far,pf_flink_server,pf_log_alert_callback_center,pf_noah_web_ui,pf_personas_build,pf_personas_web,pf_qhamster_server,pf_rule_engine_service,pf_train_joint,piao_pagent,piao_qsight_provider,pp_adam,pp_home_front,pp_u_qprivilege,pp_u_qprivilege_core,qa_qodin,qss_hris_admin,qss_isapi,qss_qfarmer,qta_order_core_p,sec_datasec,sec_firewall,sec_matrix,sp_cesar,sp_cesar_vm,sp_linkin,sp_nirvana,sp_psb,sp_wehotel_h5,sp_wehotel_web_server,t_desert_feed,t_feed_crawler,t_fortune_trade,t_guard_activity,t_guard_money,t_guard_order,t_guard_search,t_guard_supplier,t_query_order,t_sophia_money,t_sophia_order,t_sophia_search,t_sophia_supplier,t_train_am,t_train_bridge,t_train_goods,t_train_octopus,t_train_sms,t_train_snow,t_train_thalia,t_where_go,tc_advanced_tech,tc_mesh_test_consumer,tc_mesh_test_provider,tc_qtrace_web,tc_sys_health_data,teller,tr_book_task,tr_dubbo,tr_hy,u_apollo,u_apollonis,u_hydra,vc_dapp,vs_b2c_front,vs_b2c_outer_agent,vs_b2c_timertask,vs_c2b_app,vs_c2b_front,vs_comment,vs_fh_hotel_agent,vs_fh_product_service,vs_freebackend,vs_freecombine,vs_freecombine_touch,vs_promote,vs_promote_admin,vs_tts,vs_vacation_api";
    private static final Set<String> aliveAppCodeList5;

    private static int jdk17Count = 0;
    private static int jdk11Count = 0;
    private static int jdk1_8Count = 0;
    private static int jdk17Count10 = 0;
    private static int jdk11Count10 = 0;
    private static int jdk1_8Count10 = 0;
    private static int jdk17Count5 = 0;
    private static int jdk11Count5 = 0;
    private static int jdk1_8Count5 = 0;

    static {
        collectAppCodeOwner();
        aliveAppCodeList10 = Sets.newHashSet(aliveAppCodes10.split(","));
        aliveAppCodeList5 = Sets.newHashSet(aliveAppCodes5.split(","));
        collectFromAppcodeType();
    }

    public static void main(String[] args) throws Exception {
        collectAppcodeJdk();
        test();
    }

    private static void test() {
        String input2 = "/Users/fanny/Downloads/appcode_type.csv";
        EasyExcel.read(input2, AppcodeTypeRelation.class, new ReadListener<AppcodeTypeRelation>() {
            @Override
            public void invoke(AppcodeTypeRelation data, AnalysisContext analysisContext) {
                if ("tomcat".equals(data.getAppType()) && !"是".equals(data.getIfOffline())) {
                    countLevelAppcodeJdk(data.getAppcode(), appcodeJdk.get(data.getAppcode()));
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        System.out.printf("%s, %s, %s\n", jdk17Count, jdk11Count, jdk1_8Count);
        System.out.printf("%s, %s, %s\n", jdk17Count10, jdk11Count10, jdk1_8Count10);
        System.out.printf("%s, %s, %s\n", jdk17Count5, jdk11Count5, jdk1_8Count5);
    }

    /**
     * 收集appcode的jdk版本按部门划分
     *
     * @throws Exception 异常
     */
    private static void collectAppcodeJdk() throws Exception {
        Map<String, List<Integer>> ownerStatistic10 = Maps.newHashMap();
        Map<String, List<Integer>> thirdBuStatistic10 = Maps.newHashMap();
        Map<String, List<Integer>> ownerStatistic5 = Maps.newHashMap();
        Map<String, List<Integer>> thirdBuStatistic5 = Maps.newHashMap();
        String input = "/Users/fanny/Downloads/appcode_jdk.txt";
        FileInputStream inputStream = new FileInputStream(input);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] appcode_jdk = line.split(",");
            if (appcode_jdk.length != 2) {
                continue;
            }
            String appcode = appcode_jdk[0];
            String jdk = appcode_jdk[1];
            if (StringUtils.isBlank(appcode) || StringUtils.isBlank(jdk)) {
                continue;
            }
            appcodeJdk.put(appcode, jdk);
            String owner = appcodeOwner.get(appcode);
            String thirdBu = appcodeThirdBu.get(appcode);
            if (aliveAppCodeList10.contains(appcode)) {
                judgeAndSet(ownerStatistic10, appcode, jdk, owner);
                judgeAndSet(thirdBuStatistic10, appcode, jdk, thirdBu);
            }
            if (aliveAppCodeList5.contains(appcode)) {
                judgeAndSet(ownerStatistic5, appcode, jdk, owner);
                judgeAndSet(thirdBuStatistic5, appcode, jdk, thirdBu);
            }
        }
//        printJdk(ownerStatistic10);
//        System.out.println("----------------");
//        printJdk(thirdBuStatistic10);
//        System.out.println("----------------");
//        printJdk(ownerStatistic5);
//        System.out.println("----------------");
//        printJdk(thirdBuStatistic5);
//        System.out.printf("%s, %s, %s\n", jdk17Count, jdk11Count, jdk1_8Count);
//        System.out.printf("%s, %s, %s\n", jdk17Count10, jdk11Count10, jdk1_8Count10);
//        System.out.printf("%s, %s, %s\n", jdk17Count5, jdk11Count5, jdk1_8Count5);
    }

    private static void countLevelAppcodeJdk(String appcode, String jdk) {
        if ("17".equals(jdk)) {
            if ("P1".equals(appcodeLevel.get(appcode)) || "P2".equals(appcodeLevel.get(appcode))) {
                jdk17Count++;
                if (aliveAppCodeList10.contains(appcode)) {
                    jdk17Count10++;
                }
                if (aliveAppCodeList5.contains(appcode)) {
                    jdk17Count5++;
                }
            }
        }
        if ("11".equals(jdk)) {
            if ("P1".equals(appcodeLevel.get(appcode)) || "P2".equals(appcodeLevel.get(appcode))) {
                jdk11Count++;
                if (aliveAppCodeList10.contains(appcode)) {
                    jdk11Count10++;
                }
                if (aliveAppCodeList5.contains(appcode)) {
                    jdk11Count5++;
                }
            }
        }
        if ("1.8".equals(jdk)) {
            if ("P1".equals(appcodeLevel.get(appcode)) || "P2".equals(appcodeLevel.get(appcode))) {
                jdk1_8Count++;
                if (aliveAppCodeList10.contains(appcode)) {
                    jdk1_8Count10++;
                }
                if (aliveAppCodeList5.contains(appcode)) {
                    jdk1_8Count5++;
                }
            }
        }
    }

    private static void printJdk(Map<String, List<Integer>> map) {
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2), entry.getValue().get(3), entry.getValue().get(4));
        }
    }

    private static void judgeAndSet(Map<String, List<Integer>> map, String appcode, String jdk, String key) {
        if (map.get(key) == null) {
            map.put(key, Lists.newArrayList(0, 0, 0, 0, 0));
        }
        putJdkInMap(map, key, jdk);
    }

    private static void putJdkInMap(Map<String, List<Integer>> map, String key, String jdk) {
        switch (jdk) {
            case "17":
                map.get(key).set(0, map.get(key).get(0) + 1);
                break;
            case "11":
                map.get(key).set(1, map.get(key).get(1) + 1);
                break;
            case "1.8":
                map.get(key).set(2, map.get(key).get(2) + 1);
                break;
            case "1.7":
                map.get(key).set(3, map.get(key).get(3) + 1);
                break;
            case "1.6":
                map.get(key).set(4, map.get(key).get(4) + 1);
                break;
        }
    }

    /**
     * 收集已经容器化且提供域名的Java应用数
     *
     * @throws Exception 异常
     */
    private static void collectDockerJavaWithDomain() throws Exception {
        Set<String> allDomainAppCodes = Sets.newHashSet();
        String input = "/Users/fanny/Downloads/domain_appcode_relation.csv";
        EasyExcel.read(input, OutputData.class, new ReadListener<OutputData>() {
            @Override
            public void invoke(OutputData data, AnalysisContext analysisContext) {
                allDomainAppCodes.add(data.getAppcode());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        Set<String> allJavaDockerAppCodes = Sets.newHashSet();
        String input2 = "/Users/fanny/Downloads/appcode_type.csv";
        EasyExcel.read(input2, AppcodeTypeRelation.class, new ReadListener<AppcodeTypeRelation>() {
            @Override
            public void invoke(AppcodeTypeRelation data, AnalysisContext analysisContext) {
                if ("tomcat".equals(data.getAppType()) && !"是".equals(data.getIfOffline()) && data.getDockerCount() > 0) {
                    allJavaDockerAppCodes.add(data.getAppcode());
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        System.out.println("所有已容器化Java应用个数：" + allJavaDockerAppCodes.size());
        Set<String> allJavaDockerWithDomainList = Sets.newHashSet();
        for (String appcode : allDomainAppCodes) {
            if (allJavaDockerAppCodes.contains(appcode)) {
                allJavaDockerWithDomainList.add(appcode);
            }
        }
        System.out.println("所有容器化且有域名的Java应用个数：" + allJavaDockerWithDomainList.size());
        Set<String> allJavaDockerWithDomainList10 = Sets.newHashSet();
        for (String appcode : allJavaDockerWithDomainList) {
            if (aliveAppCodeList10.contains(appcode)) {
                allJavaDockerWithDomainList10.add(appcode);
            }
        }
        Set<String> allJavaDockerWithDomainList5 = Sets.newHashSet();
        for (String appcode : allJavaDockerWithDomainList) {
            if (aliveAppCodeList5.contains(appcode)) {
                allJavaDockerWithDomainList5.add(appcode);
            }
        }
        System.out.println("月发布超10次AppCode总数：" + allJavaDockerWithDomainList10.size());
        System.out.println("月发布超5次AppCode总数：" + allJavaDockerWithDomainList5.size());
        Map<String, List<Integer>> forthOwnerStatistic = Maps.newHashMap();
        Map<String, List<Integer>> thirdBuStatistic = Maps.newHashMap();
        for (String appcode : allJavaDockerAppCodes) {
            String owner = appcodeOwner.get(appcode);
            String thirdBu = appcodeThirdBu.get(appcode);
            if (StringUtils.isBlank(owner) || StringUtils.isBlank(thirdBu)) {
                System.out.println("Empty owner existed: " + appcode + " " + owner + " " + thirdBu);
                continue;
            }
            if (forthOwnerStatistic.get(owner) == null) {
                forthOwnerStatistic.put(owner, Lists.newArrayList(0, 0, 0, 0));
                forthOwnerStatistic.get(owner).set(0, 1);
            } else {
                forthOwnerStatistic.get(owner).set(0, forthOwnerStatistic.get(owner).get(0) + 1);
            }
            if (thirdBuStatistic.get(thirdBu) == null) {
                thirdBuStatistic.put(thirdBu, Lists.newArrayList(0, 0, 0, 0));
                thirdBuStatistic.get(thirdBu).set(0, 1);
            } else {
                thirdBuStatistic.get(thirdBu).set(0, thirdBuStatistic.get(thirdBu).get(0) + 1);
            }
        }
        for (String appcode : allJavaDockerWithDomainList) {
            String owner = appcodeOwner.get(appcode);
            String thirdBu = appcodeThirdBu.get(appcode);
            if (StringUtils.isBlank(owner) || StringUtils.isBlank(thirdBu)) {
                System.out.println("Empty owner existed: " + appcode + " " + owner + " " + thirdBu);
                continue;
            }
            forthOwnerStatistic.get(owner).set(1, forthOwnerStatistic.get(owner).get(1) + 1);
            thirdBuStatistic.get(thirdBu).set(1, thirdBuStatistic.get(thirdBu).get(1) + 1);
        }
        for (String appcode : allJavaDockerWithDomainList10) {
            String owner = appcodeOwner.get(appcode);
            String thirdBu = appcodeThirdBu.get(appcode);
            if (StringUtils.isBlank(owner) || StringUtils.isBlank(thirdBu)) {
                System.out.println("Empty owner existed: " + appcode + " " + owner + " " + thirdBu);
                continue;
            }
            forthOwnerStatistic.get(owner).set(2, forthOwnerStatistic.get(owner).get(2) + 1);
            thirdBuStatistic.get(thirdBu).set(2, thirdBuStatistic.get(thirdBu).get(2) + 1);
        }
        for (String appcode : allJavaDockerWithDomainList5) {
            String owner = appcodeOwner.get(appcode);
            String thirdBu = appcodeThirdBu.get(appcode);
            if (StringUtils.isBlank(owner) || StringUtils.isBlank(thirdBu)) {
                System.out.println("Empty owner existed: " + appcode + " " + owner + " " + thirdBu);
                continue;
            }
            forthOwnerStatistic.get(owner).set(3, forthOwnerStatistic.get(owner).get(3) + 1);
            thirdBuStatistic.get(thirdBu).set(3, thirdBuStatistic.get(thirdBu).get(3) + 1);
        }
        for (Map.Entry<String, List<Integer>> entry : forthOwnerStatistic.entrySet()) {
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", ownerForthBu.get(entry.getKey()), entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2), entry.getValue().get(3));
        }
        System.out.println("-------------------------");
        for (Map.Entry<String, List<Integer>> entry : thirdBuStatistic.entrySet()) {
            System.out.printf("%s\t%s\t%s\t%s\t%s\n", entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2), entry.getValue().get(3));
        }
    }

    private static void collectAppCodeOwner() {
        String input = "/Users/fanny/Downloads/应用复杂度详情-表格 1.csv";
        EasyExcel.read(input, AppcodeBuRelation.class, new ReadListener<AppcodeBuRelation>() {
            @Override
            public void invoke(AppcodeBuRelation data, AnalysisContext analysisContext) {
                appcodeOwner.put(data.getAppcode(), data.getOwner());
                appcodeThirdBu.put(data.getAppcode(), data.getThird_bu());
                appcodeForthBu.put(data.getAppcode(), data.getForth_bu());
                ownerForthBu.put(data.getOwner(), data.getForth_bu());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
    }

    /**
     * 收集域名与appcode映射关系
     *
     * @throws Exception 异常
     */
    private static void collectDomainAppcodeRelation() throws Exception {
        Map<String, String> appcodeRelation = Maps.newHashMap();
        String input = "/Users/fanny/Downloads/应用复杂度详情-表格 1.csv";
        EasyExcel.read(input, AppcodeBuRelation.class, new ReadListener<AppcodeBuRelation>() {
            @Override
            public void invoke(AppcodeBuRelation data, AnalysisContext analysisContext) {
                handleMap(appcodeRelation, data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        String domainAppcodeRelationInput = "/Users/fanny/Downloads/one";
        FileInputStream inputStream = new FileInputStream(domainAppcodeRelationInput);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        List<OutputData> outputList = Lists.newArrayList();
        while ((line = bufferedReader.readLine()) != null) {
            OutputData data = construct(line);
            if (data == null) {
                continue;
            }
            String bu = appcodeRelation.get(data.getAppcode());
            if (bu == null) {
                System.out.println("Null bu existed, appcode=>" + data.getAppcode());
                continue;
            }
            String[] buInfo = bu.split("__");
            data.setThirdBu(buInfo[0]);
            data.setForthBu(buInfo[1]);
            outputList.add(data);
        }
        String output = "/Users/fanny/Downloads/domain_appcode_relation.csv";
        EasyExcel.write(output, OutputData.class).sheet().doWrite(outputList);
    }

    private static void handleMap(Map<String, String> map, AppcodeBuRelation data) {
        map.put(data.getAppcode(), String.format("%s__%s", data.getThird_bu(), data.getForth_bu()));
    }

    private static OutputData construct(String line) {
        String[] data = line.split("\\s+");
        if (data.length != 2) {
            System.out.println("Error line existed, line=>" + line);
            return null;
        }
        String[] appcodeInfo = data[1].split("__");
        if (appcodeInfo.length != 3) {
            System.out.println("Error appcodeInfo existed, info=>" + data[1]);
            return null;
        }
        String appcode = appcodeInfo[1];
        if (appcode == null) {
            System.out.println("Null appcode existed, line=>" + line);
            return null;
        }
        appcode = appcode.replaceAll("-", "_");
        OutputData outputData = new OutputData();
        outputData.setAppcode(appcode);
        outputData.setDomain(data[0]);
        return outputData;
    }

    private static void collectFromAppcodeType() {
        String input = "/Users/fanny/Downloads/appcode_type.csv";
        EasyExcel.read(input, AppcodeTypeRelation.class, new ReadListener<AppcodeTypeRelation>() {
            @Override
            public void invoke(AppcodeTypeRelation data, AnalysisContext analysisContext) {
                appcodeLevel.put(data.getAppcode(), data.getLevel());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
    }
}
