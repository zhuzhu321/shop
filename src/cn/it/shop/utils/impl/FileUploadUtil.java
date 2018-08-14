package cn.it.shop.utils.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.it.shop.model.FileImage;
import cn.it.shop.utils.FileUpload;

/**
 * @Description: TODO(ʵ���ļ��ϴ���ҵ���߼�)
 * @author Ni Shengwu
 *
 */
@Component("fileUpload")
public class FileUploadUtil implements FileUpload {
	
	//@Value��ʾȥbeans.xml�ļ�����id="prop"��bean������ͨ��ע��ķ�ʽ��ȡproperties�����ļ��ģ�Ȼ��ȥ��Ӧ�������ļ��ж�ȡkey=filePath��ֵ
	@Value("#{prop.basePath+prop.filePath}") 
	private String filePath;
	
	@Value("#{prop.basePath+prop.bankImagePath}")
	private String bankImagePath;
	
//	public void setFilePath(String filePath) {
//		System.out.println(filePath);
//		this.filePath = filePath;
//	}
//	
//	public void setBankImagePath(String bankImagePath) {
//		System.out.println(bankImagePath);
//		this.bankImagePath = bankImagePath;
//	}
	
	public String[] getBankImage() {
		String[] list  = new File(bankImagePath).list(new FilenameFilter() {
			
			//����ָ���ļ��Ƿ�Ӧ�ð�����ĳһ�ļ��б���
			@Override
			public boolean accept(File dir, String name) {
				System.out.println("dir:" + dir + ",name:" + name);				
				//ͨ����׺����ʵ���ļ��Ĺ���Ч��
				//������ͷŵ�list�У����ؼپ͹��˵�
				return name.endsWith(".gif");
			}
		});
		return list;
	}
	
	//ʵ���ļ��ϴ��Ĺ��ܣ������ϴ����µ��ļ�����
	@Override
	public String uploadFile(FileImage fileImage) {
		//��ȡ��Ψһ�ļ���
		String pic = newFileName(fileImage.getFilename());
		try {
			FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));
			return pic;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			fileImage.getFile().delete();
		}
	}
	
	//1. ͨ���ļ�����ȡ��չ��
	private String getFileExt(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}
	
	//2. ����UUID���������Ϊ�µ��ļ���
	private String newFileName(String fileName) {
		String ext = getFileExt(fileName);
		return UUID.randomUUID().toString() + "." + ext;
	}
	
}
