package com.mkfree.framework.common.upload;

import java.io.File;
import java.util.Calendar;

import com.mkfree.framework.common.constants.AppUploadConstants;

public class UploadUtil {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		UploadUtil util = new UploadUtil();
		System.out.println(util.getUploadPath("/opt/virtual/upload/", "blog/posts/", 10, 3));
		String fileName = "sadfjlsadfj.sdfsdf.sjadf.jpg";
		System.out.println(util.getUploadFileName(fileName));
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	/**
	 * 通过一系列参数,获取对应的上传路径,上传路径,是要通过程序去计算出来(计算规则已经写了在文档里)
	 * 
	 * @param basePath 上传附件设定的路径,我的目录结构设置为/opt/virtual/upload/
	 * @param rootPath 应用上传文件的根目录 blog/posts/
	 * @param saveCount 一个目录保存多少文件
	 * @param dirLevel 目录层次
	 * @return
	 */
	public String getUploadPath(String basePath, String rootPath, int saveCount, int dirLevel) {
		File rootPathFile = new File(basePath + rootPath);
		String saveFilePath = this.getLastDir(rootPathFile, dirLevel);// 首先这个是,计算出来,要保存在那个目录,通常000/000/000,我通过rootPath去计算
		String uploadPath = saveFilePath;
		File currentFile = new File(uploadPath);
		if (!currentFile.exists()) {
			currentFile.mkdirs();
		}
		if (currentFile.isDirectory() && currentFile.listFiles().length > saveCount) {
			String[] findPaths = saveFilePath.split(AppUploadConstants.UPLOAD_PATHSPLIT + "\\");
			for (int i = findPaths.length - 1; i >= 0; i--) {
				int dirNum = Integer.parseInt(findPaths[i]);
				if (dirNum == saveCount) {// 当前文件夹目录数大于自定义目录数,我应该要在上上层目录创建新目录
					int rootDirNum = Integer.parseInt(findPaths[i - 1]);
					if (rootDirNum == saveCount) {
						continue;
					}
					// 例如:dirLevel = 4 ,那么i = 3 ,再减去1就是获取上两层目录
					File rootDir = this.getRootDir(dirLevel - (i - 1), currentFile);
					int nextDir = rootDirNum + 1;// 创建新目录,按当前目录加1
					int addDir = dirLevel - i;// 需要添加回几层目录
					return uploadPath = this.getUploadPath(rootDir, nextDir, addDir);
				} else {// 当前文件夹文件数大于自定义目录文件数,我应该要在上一层目录创建新目录
					File rootDir = currentFile.getParentFile();
					int nextDir = Integer.parseInt(findPaths[i]) + 1;
					StringBuffer temp = this.getNextDir(rootDir, nextDir);
					uploadPath = temp.toString();
					return uploadPath;
				}
			}
		}
		return uploadPath;
	}

	/**
	 * 通过父级路径,获取子路径
	 * 
	 * @param parentFile
	 * @return
	 */
	private File childFile(File parentFile) {
		String childFilePath = "";
		if (parentFile.listFiles() != null) {
			int listFileSize = parentFile.listFiles().length;
			if (listFileSize > 0) {
				listFileSize = listFileSize - 1;
			}
			childFilePath = this.getNextDir(parentFile, listFileSize).toString();
		} else {
			childFilePath = parentFile.getPath() + AppUploadConstants.UPLOAD_PATHSPLIT + AppUploadConstants.UPLOAD_000;
		}
		File childFile = new File(childFilePath);
		return childFile;
	}

	/**
	 * 通过父级文件路径,计算出最终的保存目录
	 * 
	 * @param parentFile 父级文件
	 * @param dirLevel 获取多少层,子路径
	 * @return
	 */
	private String getLastDir(File parentFile, int dirLevel) {
		// 首先,取出应用上传目录
		// 1.查检当前目录下的文件夹数,有多少,一层层的计算出来
		// 例如:上层是/opt/virtual/upload/blog/posts/ -->>
		// /opt/virtual/upload/blog/posts/000/ -->>
		// /opt/virtual/upload/blog/posts/000/000/ -->>
		// /opt/virtual/upload/blog/posts/000/000/000/
		File temp = null;
		for (int i = 0; i < dirLevel; i++) {
			if (temp != null) {
				temp = this.childFile(temp);
			} else {
				temp = this.childFile(parentFile);
			}
		}
		return temp.getPath();
	}

	/**
	 * 通过父级目录获取上传的全路径
	 * 
	 * @param rootDir 父级目录
	 * @param nextDir 增加1的目录
	 * @param addDir 在nextDir的基本上,添回后面的几层目录,保存完成性
	 * @return
	 */
	private String getUploadPath(File rootDir, int nextDir, int addDir) {
		StringBuffer temp = this.getNextDir(rootDir, nextDir);
		for (int j = 0; j < addDir; j++) {
			temp.append(AppUploadConstants.UPLOAD_000).append(AppUploadConstants.UPLOAD_PATHSPLIT);
		}
		return temp.toString();
	}

	/**
	 * 通过当前文件,获取上级文件目录,可能会有多层目录结构
	 * 
	 * @param dirLevel 获取父级几层
	 * @param currentFile 当前文件
	 * @return
	 */
	private File getRootDir(int dirLevel, File currentFile) {
		File rootFile = null;
		for (int j = 0; j < dirLevel; j++) {
			if (rootFile != null) {
				rootFile = rootFile.getParentFile();
			} else {
				rootFile = currentFile.getParentFile();
			}
		}
		return rootFile;
	}

	/**
	 * 通过父级目录,创建新的下级目录
	 * 
	 * @param rootDir 父级目录
	 * @param nextDir 下级目录,例如 000/000/000/000 返回 000/000/000/001
	 * @return
	 */
	private StringBuffer getNextDir(File rootDir, int nextDir) {
		StringBuffer temp = new StringBuffer();
		temp.append(rootDir.getPath()).append(AppUploadConstants.UPLOAD_PATHSPLIT);
		if (nextDir < 10) {// 当前目录<10的时候,前面补两个零
			temp.append(AppUploadConstants.UPLOAD_00).append(nextDir);
		} else if (nextDir < 100) {// 当前目录<100的时候,前面补两个零
			temp.append(AppUploadConstants.UPLOAD_0).append(nextDir);
		}
		temp.append(AppUploadConstants.UPLOAD_PATHSPLIT);
		return temp;
	}

	public String upload(String file, String rootPath, int saveCount, int dirLevel) {
		return null;
	}

	/**
	 * 获取文件上传文件名
	 * 
	 * @return
	 */
	public String getUploadFileName(String file) {
		Calendar c = Calendar.getInstance();
		StringBuffer fileName = new StringBuffer();
		fileName.append(c.get(Calendar.YEAR)).append("-").append(c.get(Calendar.MONTH) + 1).append("-").append(c.get(Calendar.DATE)).append("-")
				.append(c.get(Calendar.HOUR_OF_DAY)).append("-").append(c.get(Calendar.MINUTE)).append("-").append(c.get(Calendar.SECOND)).append("-")
				.append(c.get(Calendar.MILLISECOND));
		fileName.append(this.getExpandedName(file));
		return fileName.toString();
	}

	/**
	 * 获取文件的扩展名
	 * 
	 * @param file
	 * @return
	 */
	public String getExpandedName(String file) {
		String expandeName = null;
		if (file != null && file.length() > 0) {
			expandeName = file.substring(file.lastIndexOf("."));
		}

		return expandeName;
	}

	/**
	 * 获取文件的扩展名
	 * 
	 * @param file
	 * @return
	 */
	public String getFileType(String file) {
		String fileType = null;
		if (file != null && file.length() > 0) {
			fileType = file.substring(file.lastIndexOf(".") + 1);
		}

		return fileType;
	}
}
