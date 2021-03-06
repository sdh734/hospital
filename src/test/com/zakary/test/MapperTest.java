package com.zakary.test;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.zakary.dao.*;
import com.zakary.dao.utils.DoctorPatients;
import com.zakary.services.DepartmentService;
import com.zakary.services.DoctorService;
import com.zakary.services.PatientService;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;

public class MapperTest extends BaseTest {
    @Autowired
    private DepartmentService departmentService;
//    @Autowired
//    private DoctorService doctorService;
    private Logger logger;
    @Autowired
    private PatientService patientService;
    @Test
    public void test1(){
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.setDepartment_name("部门3");
        departmentService.insertDepartment(departmentDao);
    }
    @Test
    public void test2(){
        PositionDao positionDao = new PositionDao();
        positionDao.setDepartment_name("部门2");
        positionDao.setDepartment_id(1);
        positionDao.setPosition_name("部门2的职位1");
        departmentService.insertPosition(positionDao);
    }
    @Test
    public void test3(){
        System.out.println(departmentService.getDepartments());
    }
    @Test
    public void test4(){
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.setDepartment_id(1);
        System.out.println(departmentService.getPositions(departmentDao));
    }
    @Test
    public void test5(){
        for(int i=1;i<=10000;i++){
            DoctorDao doctorDao = new DoctorDao();
            doctorDao.setDoctor_position("doctorPosition"+i);
            doctorDao.setDoctor_name("doctor"+i);
            doctorDao.setDoctor_tel("12345678912"); 
            doctorDao.setType(0);
            doctorDao.setCert_code(i+"");
            doctorDao.setDoctor_department("doctorDepartment"+i);
            doctorDao.setPassword("e10adc3949ba59abbe56e057f20f883e");
            doctorDao.setDoctor_gender("男");
//            doctorService.insertDoctor(doctorDao);
        }
    }
    @Test
    public void test6(){
        String id="2";
        int pageNum=1;
        int limit=10;
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        pageDao.setDoctor_cert_code("2");
        List<PatientDao> patients=patientService.getAllPatientByDoctorCert(pageDao);
        System.out.println(patients);
    }
    @Test
    public void test7(){
        Date date=new Date(2000,1,1);
        System.out.println(date);
       TreatmentDao treatmentDao=new TreatmentDao();
        treatmentDao.setDoctor_cert_code("2");
        treatmentDao.setPatient_cert_code("8");
        treatmentDao.setTreatment_name("zakary");
        treatmentDao.setTreatment_time(date);
        treatmentDao.setTreatment_fee(600.0);
        patientService.insertPatient(treatmentDao);
    }
    @Test
    public void test8(){
        SickbedDao sickbedDao=new SickbedDao();
        sickbedDao.setPatient_cert_code("5");
        sickbedDao.setSickroom_id(2);
        sickbedDao.setSickbed_id(2);
        patientService.arrangeSickbed(sickbedDao);
    }
    @Test
    public void test9(){
        int pageNum=1;
        int limit=10;

        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);

        patientService.getPatientsSickbedInfo(pageDao);
    }

    @Test
    public void test10(){
        //PrescriptionDao prescriptionDao=new PrescriptionDao();
        PrescriptionAttributeDao prescriptionAttributeDao=new PrescriptionAttributeDao();
        prescriptionAttributeDao.setDoctor_cert_code("2");
        prescriptionAttributeDao.setPatient_cert_code("1");
        prescriptionAttributeDao.setDrug_num(5);
        prescriptionAttributeDao.setDrug_name("drug1");
        prescriptionAttributeDao.setPrescription_id(1);
        System.out.println(prescriptionAttributeDao.getDrug_name());
        patientService.insertPrescriptionAttribute(prescriptionAttributeDao);
    }

    @Test
    public void test11(){
        //Logger logger = Logger.getLogger("");
        PrescriptionAttributeDao prescriptionAttributeDao=new PrescriptionAttributeDao();
        prescriptionAttributeDao.setPrescription_id(1);
        prescriptionAttributeDao.setDoctor_cert_code("2");
        prescriptionAttributeDao.setPatient_cert_code("1");
        List<Map<String,Object>> infos= patientService.getAllPrescriptionAttribute(prescriptionAttributeDao);
        double alldrugprice=0;
        for(int i=0;i<infos.size();i++) {
            //Logger logger=Logger.getLogger("getAllPrescriptionAttribute");
            //logger.info("DRUG :"+infos.get(i));
            double price=Double.parseDouble(infos.get(i).get("drug_price").toString());
            int num=Integer.parseInt(infos.get(i).get("drug_num").toString());
            alldrugprice+=price*num;
            //alldrugprice+=infos.get(i).get(drug_price)*infos.get(i).get(drug_num);
        }
        //Logger logger=Logger.getLogger("drug_price");
        //logger.info("总价 ："+alldrugprice);
    }
    @Test
    public void test12(){
        //Logger logger = Logger.getLogger("");
        HlistDao hlistDao=new HlistDao();
        hlistDao.setPatient_cert_code("1");
        patientService.setHlistByCert(hlistDao);
    }
    @Test
    public void test13(){
        //Logger logger = Logger.getLogger("");
        HlistDao hlistDao= patientService.getHlistByCert("1");
        //logger.info(hlistDao.toString());
    }

    @Test
    public void test14(){
        PageDao pageDao=new PageDao();
        pageDao.setLimit(10);
        pageDao.setPage(0);
//        pageDao.setPatient_cert_code("6");
        pageDao.setDoctor_cert_code("2");
        List<Map<String,Object>> infos=patientService.getAllPatientSickbed(pageDao);
        System.out.println(infos);
        System.out.println(infos.size());
    }

    @Test
    public void test15(){
        PageDao pageDao=new PageDao();
        pageDao.setLimit(10);
        pageDao.setPage(0);
        List<Map<String,Object>> infos=patientService.getAllPatientNoSickbed(pageDao);
        for (Map<String, Object> m : infos) {
            for (String k : m.keySet()) {
                //logger.info(k+": "+m.get(k));
                System.out.print(" "+k+": "+m.get(k));
            }
            System.out.println();
        }
    }

    @Test
    public void test16(){
        PatientDao patientDao=new PatientDao();
        String doctor_cert_code="2";
        patientDao.setDoctor_cert_code("2");
        patientDao.setCert_code("8");
        patientDao.setPatient_name("p8");
        patientDao.setPatient_age(19);
        patientDao.setPatient_gender("男");
        patientDao.setPatient_tel("123");
        patientService.addPatient(doctor_cert_code,patientDao);
    }

    @Test
    public void test17(){
        logger=LoggerFactory.getLogger(getClass());
        TreatmentDao treatmentDao=new TreatmentDao();
        treatmentDao.setPatient_cert_code("2");
        List<Map<String, Object>> all=patientService.getAllTreatmentByPatientCertCode(treatmentDao);
        logger.info(""+all);


    }

    @Test
    public void test18(){
        PatientDao patientDao=new PatientDao();
        patientDao.setCert_code("7");
        patientDao.setOld_cert_code("7");
        patientDao.setPatient_name("p8");
        patientDao.setPatient_gender("男");
        patientDao.setPatient_age(12);
        patientDao.setPatient_tel("110");
        patientService.alterPatientInfoByCert(patientDao);
    }

    @Test
    public void test19(){
        logger=LoggerFactory.getLogger(getClass());
        PageDao pageDao = new PageDao();
        pageDao.setLimit(10);
        pageDao.setPage(0);
        pageDao.setPatient_cert_code("8");
        pageDao.setDoctor_cert_code("2");
        List<Map<String,Object>> TreatmentCount=patientService.getTreatmentCountByCert(pageDao);
        System.out.println(TreatmentCount);
    }

    @Test
    public void test20(){
        PrescriptionDao prescriptionDao=new PrescriptionDao();
        prescriptionDao.setPatient_cert_code("1");
        List<PrescriptionDao> prescriptionDaos=patientService.getAllPrescriptionByCert(prescriptionDao);
        logger=LoggerFactory.getLogger(getClass());
        logger.info(prescriptionDaos.size()+"");
    }

    @Test
    public void test21(){
        PrescriptionAttributeDao prescriptionAttributeDao=new PrescriptionAttributeDao();
        prescriptionAttributeDao.setPrescription_id(13);
        List<PrescriptionAttributeDao> prescriptionAttributeDaos=patientService.getAllPrescriptionAttributeByPrescriptionId(prescriptionAttributeDao);
        logger=LoggerFactory.getLogger(getClass());
        logger.info(prescriptionAttributeDaos.size()+"");
    }
}
