package ui;

import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.sun.awt.*;


public class IrregularFormSample extends JFrame {
    private static final long serialVersionUID = 1L;
    private Point origin; // 用于移动窗体
    private BufferedImage img; // 用来设定窗体不规则样式的图片
    int[] pix=new int[3];

    public IrregularFormSample() throws Exception {
        super();
        /*
         * 首先初始化一张图片，我们可以选择一张有透明部分的不规则图片 (当然我们要选择支持Alpha(透明)层的图片格式，如PNG)，这张
         * 图片将被用来生成与其形状相同的不规则窗体
         */
        MediaTracker mt = new MediaTracker(this);
//      img = new ImageIcon("image/星球1.png").getImage();
        File file=new File("image/星球/星球4.png");
        img=ImageIO.read(file);

        mt.addImage(img, 0);
        try {
            mt.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            initialize();// 窗体初始化
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Thread t=new Thread();
//        t.start();
    }

    /**
     * 窗体初始化
     */
    private void initialize() throws IOException {
        // 设定窗体大小和图片一样大
        this.setSize(img.getWidth(null), img.getHeight(null));
        // 设定禁用窗体装饰，这样就取消了默认的窗体结构
        this.setUndecorated(true);
        // 设置该窗口总是在其它窗口的最上方
        this.setAlwaysOnTop(true);
        // 初始化用于移动窗体的原点
        this.origin = new Point();

        // 调用AWTUtilities的setWindowShape方法设定本窗体为制定的Shape形状
        extracted();
        // 设定窗体可见度
        extracted1();

        this.setLocationRelativeTo(null);

        // 由于取消了默认的窗体结构，所以我们要手动设置一下移动窗体的方法
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }

            // 窗体上单击鼠标右键关闭程序
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    System.exit(0);
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
                        - origin.y);
            }
        });
    }

	private void extracted() {
		AWTUtilities.setWindowOpacity(this, 1.0f);
	}

	private void extracted1() {
		AWTUtilities.setWindowShape(this, getImageShape(img));
	}

    /**
     * 将Image图像转换为Shape图形
     */
    public Shape getImageShape(BufferedImage img) {
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        int width = img.getWidth(null);// 图像宽度
        int height = img.getHeight(null);// 图像高度

        // 筛选像素
        // 首先获取图像所有的像素信息
        PixelGrabber pgr = new PixelGrabber(img, 0, 0, -1, -1, true);
        try {
            pgr.grabPixels();
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
        int pixels[] = (int[]) pgr.getPixels();

        // 循环像素
        for (int i = 0; i < pixels.length; i++) {
            // 筛选，将不透明的像素的坐标加入到坐标ArrayList x和y中
            int alpha = getAlpha(pixels[i]);
            int rgb=img.getRGB(i%width,i/width);
//            pix[0]=(rgb&0xff0000)>>16;
//        	pix[1]=(rgb&0xff00)>>8;
//        	pix[2]=(rgb&0xff);
            
//            int rgb=
//            System.out.println(rgb);
            if ((alpha != 0)/*&&(pix[0]>200)&&(pix[1]>200)&&(pix[2]>200)*/) {
                x.add(i % width > 0 ? i % width - 1 : 0);
                y.add(i % width == 0 ? (i == 0 ? 0 : i / width - 1) : i / width);
            }
        }
        // 建立图像矩阵(0为透明,1为不透明)
        int[][] matrix = new int[height][width];
        // 导入坐标ArrayList中的不透明坐标信息
        for (int c = 0; c < x.size(); c++) {
            matrix[y.get(c)][x.get(c)] = 1;
        }
        /*
         * 由于Area类所表示区域可以进行合并，我们逐一水平"扫描"图像矩阵的每一行，
         * 将不透明的像素生成为Rectangle，再将每一行的Rectangle通过Area类的rec
         * 对象进行合并，最后形成一个完整的Shape图形
         */
        Area rec = new Area();
        int temp = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 1) {
                    if (temp == 0)
                        temp = j;
                    else if (j == width) {
                        if (temp == 0) {
                            Rectangle rectemp = new Rectangle(j, i, 1, 1);
                            rec.add(new Area(rectemp));
                        } else {
                            Rectangle rectemp = new Rectangle(temp, i,
                                    j - temp, 1);
                            rec.add(new Area(rectemp));
                            temp = 0;
                        }
                    }
                } else {
                    if (temp != 0) {
                        Rectangle rectemp = new Rectangle(temp, i, j - temp, 1);
                        rec.add(new Area(rectemp));
                        temp = 0;
                    }
                }
            }
            temp = 0;
        }
        return rec;
    }

    /**
     * 获取像素的Alpha值
     */
    private int getAlpha(int pixel) {
        return (pixel >> 24) & 0xff;
    }

    /*
     * 我们可以选择在窗体上绘制图片，是窗体完全呈现出图片的样式， 当然我们也可以根据需要不绘制它，而采取其他操作
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, null);
    }

    public static void main(String[] args) throws Exception {
        IrregularFormSample sample = new IrregularFormSample();
        sample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sample.setVisible(true);
    }

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		while(true){
//			try{
//				Thread.sleep(50);
//			}
//			catch(Exception e){}
//			this.repaint();
//		}
//	}
}
