package bean;

public class Map {
	private int id;
	private String name;
	private int width;
	private int height;
	private String obstacle;
	private String fire;
	private String trap;
	private String pit;
	private int[][] map;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
		
	public String getObstacle() {
		return obstacle;
	}
	public void setObstacle(String obstacle) {
		this.obstacle = obstacle;
	}
	public String getFire() {
		return fire;
	}
	public void setFire(String fire) {
		this.fire = fire;
	}
	public String getTrap() {
		return trap;
	}
	public void setTrap(String trap) {
		this.trap = trap;
	}
	public String getPit() {
		return pit;
	}
	public void setPit(String pit) {
		this.pit = pit;
	}
	
	public int[][] getObstacle_Array() {
		int[][] o = new int[width][height];
		for(int i=0;i<width;i++) {
			for(int ii=0;ii<height;ii++) {
				if(map[i][ii]==1) {
					o[i][ii]=1;
				}
			}
		}
		return o;
	}
	public int[][] getFire_Array() {
		int[][] o = new int[width][height];
		for(int i=0;i<width;i++) {
			for(int ii=0;ii<height;ii++) {
				if(map[i][ii]==2) {
					o[i][ii]=1;
				}
			}
		}
		return o;
	}
	public int[][] getTrap_Array() {
		int[][] o = new int[width][height];
		for(int i=0;i<width;i++) {
			for(int ii=0;ii<height;ii++) {
				if(map[i][ii]==3) {
					o[i][ii]=1;
				}
			}
		}
		return o;
	}
	public int[][] getPit_Array() {
		int[][] o = new int[width][height];
		for(int i=0;i<width;i++) {
			for(int ii=0;ii<height;ii++) {
				if(map[i][ii]==4) {
					o[i][ii]=1;
				}
			}
		}
		return o;
	}
	
	public int[][] getMap() {
		return map;
	}
	public void setMap(int w,int h,String obs,String fs,String trs,String ps) {
		int[][] map = new int[w][h];
		this.obstacle = obs;
		this.fire = fs;
		this.trap = trs;
		this.pit = ps;
		String[] ob = obs.split(",");
		String[] f = fs.split(",");
		String[] tr = trs.split(",");
		String[] p = ps.split(",");
		for(String obstacle:ob) {
			if(obstacle==null||obstacle.trim().equals("")) {
				continue;
			}
			String[] o_coord = obstacle.split("x");
			String[] o_posx = o_coord[0].split("-");
			String[] o_posy = o_coord[1].split("-");
			int o_posx1 = Integer.parseInt(o_posx[0], 16);
			int o_posy1 = Integer.parseInt(o_posy[0], 16);
			int o_posx2 = Integer.parseInt(o_posx[0], 16);
			int o_posy2 = Integer.parseInt(o_posy[0], 16);
			if(o_posx.length>1) {
				o_posx2 = Integer.parseInt(o_posx[1], 16);
			}
			if(o_posy.length>1) {
				o_posy2 = Integer.parseInt(o_posy[1], 16);
			}
			for(int i=o_posy1;i<=o_posy2;i++) {
				for(int ii=o_posx1;ii<=o_posx2;ii++) {
					map[i][ii]=1;
				}
			}
		}
		for(String fire:f) {
			if(fire==null||fire.trim().equals("")) {
				continue;
			}
			String[] f_coord = fire.split("x");
			String[] f_posx = f_coord[0].split("-");
			String[] f_posy = f_coord[1].split("-");
			int f_posx1 = Integer.parseInt(f_posx[0], 16);
			int f_posy1 = Integer.parseInt(f_posy[0], 16);
			int f_posx2 = Integer.parseInt(f_posx[0], 16);
			int f_posy2 = Integer.parseInt(f_posy[0], 16);
			if(f_posx.length>1) {
				f_posx2 = Integer.parseInt(f_posx[1], 16);
			}
			if(f_posy.length>1) {
				f_posy2 = Integer.parseInt(f_posy[1], 16);
			}
			for(int i=f_posy1;i<=f_posy2;i++) {
				for(int ii=f_posx1;ii<=f_posx2;ii++) {
					map[i][ii]=2;
				}
			}
		}
		for(String trap:tr) {
			if(trap==null||trap.trim().equals("")) {
				continue;
			}
			String[] t_coord = trap.split("x");
			String[] t_posx = t_coord[0].split("-");
			String[] t_posy = t_coord[1].split("-");
			int t_posx1 = Integer.parseInt(t_posx[0], 16);
			int t_posy1 = Integer.parseInt(t_posy[0], 16);
			int t_posx2 = Integer.parseInt(t_posx[0], 16);
			int t_posy2 = Integer.parseInt(t_posy[0], 16);
			if(t_posx.length>1) {
				t_posx2 = Integer.parseInt(t_posx[1], 16);
			}
			if(t_posy.length>1) {
				t_posy2 = Integer.parseInt(t_posy[1], 16);
			}
			for(int i=t_posy1;i<=t_posy2;i++) {
				for(int ii=t_posx1;ii<=t_posx2;ii++) {
					map[i][ii]=3;
				}
			}
		}
		for(String pit:p) {
			if(pit==null||pit.trim().equals("")) {
				continue;
			}
			String[] p_coord = pit.split("x");
			String[] p_posx = p_coord[0].split("-");
			String[] p_posy = p_coord[1].split("-");
			int p_posx1 = Integer.parseInt(p_posx[0], 16);
			int p_posy1 = Integer.parseInt(p_posy[0], 16);
			int p_posx2 = Integer.parseInt(p_posx[0], 16);
			int p_posy2 = Integer.parseInt(p_posy[0], 16);
			if(p_posx.length>1) {
				p_posx2 = Integer.parseInt(p_posx[1], 16);
			}
			if(p_posy.length>1) {
				p_posy2 = Integer.parseInt(p_posy[1], 16);
			}
			for(int i=p_posy1;i<=p_posy2;i++) {
				for(int ii=p_posx1;ii<=p_posx2;ii++) {
					map[i][ii]=4;
				}
			}
		}
		this.map = map;
	}
	
	public void setMap(int[][] map) {
		this.map = map;
		int i = 0,ii = 0;
		String obsh="",fsh="",trsh="",psh="";
		String obsv="",fsv="",trsv="",psv="";
		for(int[] arr:map) {
			if(ii<arr.length) {
				ii = arr.length;
			}
			i++;
		}
		width = i;
		height = ii;
		System.out.println(width+"x"+height);
		for(i=0;i<width;i++) {
			for(ii=0;ii<height;ii++) {
				if(map[ii][i]==1) {
					obsv+=Integer.toHexString(i)+"x"+Integer.toHexString(ii);
					if(ii+1<height && map[ii+1][i]==1) {
						obsv+="-";
						while(ii+1<height && map[ii+1][i]==1) {
							ii++;
						}
						obsv+=Integer.toHexString(ii);
					}
					obsv+=",";
				}
				if(map[ii][i]==2) {
					fsv+=Integer.toHexString(i)+"x"+Integer.toHexString(ii);
					if(ii+1<height && map[ii+1][i]==2) {
						fsv+="-";
						while(ii+1<height && map[ii+1][i]==2){
							ii++;
						}
						fsv+=Integer.toHexString(ii);
					}
					fsv+=",";
				}
				if(map[ii][i]==3) {
					trsv+=Integer.toHexString(i)+"x"+Integer.toHexString(ii);
					if(ii+1<height && map[ii+1][i]==3) {
						trsv+="-";
						while(ii+1<height && map[ii+1][i]==3){
							ii++;
						}
						trsv+=Integer.toHexString(ii);
					}
					trsv+=",";
				}
				if(map[ii][i]==4) {
					psv+=Integer.toHexString(i)+"x"+Integer.toHexString(ii);
					if(ii+1<height && map[ii+1][i]==4) {
						psv+="-";
						while(ii+1<height && map[ii+1][i]==4){
							ii++;
						}
						psv+=Integer.toHexString(ii);
					}
					psv+=",";
				}
			}
		}
		for(ii=0;ii<height;ii++) {
			for(i=0;i<width;i++) {
				if(map[ii][i]==1) {
					obsh+=Integer.toHexString(i);
					if(i+1<width && map[ii][i+1]==1) {
						obsh+="-";
						while(i+1<width && map[ii][i+1]==1) {
							i++;
						}
						obsh+=Integer.toHexString(i);
					}
					obsh+="x"+Integer.toHexString(ii)+",";
				}
				if(map[ii][i]==2) {
					fsh+=Integer.toHexString(i);
					if(i+1<width && map[ii][i+1]==2) {
						fsh+="-";
						while(i+1<width && map[ii][i+1]==2) {
							i++;
						}
						fsh+=Integer.toHexString(i);
					}
					fsh+="x"+Integer.toHexString(ii)+",";
				}
				if(map[ii][i]==2) {
					trsh+=Integer.toHexString(i);
					if(i+1<width && map[ii][i+1]==3) {
						trsh+="-";
						while(i+1<width && map[ii][i+1]==3) {
							i++;
						}
						trsh+=Integer.toHexString(i);
					}
					trsh+="x"+Integer.toHexString(ii)+",";
				}
				if(map[ii][i]==4) {
					psh+=Integer.toHexString(i);
					if(i+1<width && map[ii][i+1]==4) {
						psh+="-";
						while(i+1<width && map[ii][i+1]==4) {
							i++;
						}
						psh+=Integer.toHexString(i);
					}
					psh+="x"+Integer.toHexString(ii)+",";
				}
			}
		}
		obstacle = obsh.length()<obsv.length()?obsh:obsv;
		fire = fsh.length()<fsv.length()?fsh:fsv;
		trap = trsh.length()<trsv.length()?trsh:trsv;
		pit = psh.length()<psv.length()?psh:psv;
	}
	
	
}
