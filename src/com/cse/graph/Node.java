package com.cse.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 图中的节点类
 * 
 * @author 作者 E-mail:
 * @date 创建时间： 2016-3-17 下午3:04:20
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Node implements Comparable<Node> {
	int id; // Node id

	// 与当前节点相邻的所有节点的ID，以及边长的
	Map<Integer, Double> neiborNodeId = new HashMap<Integer, Double>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, Double> getNeiborNodeId() {
		return neiborNodeId;
	}

	public void setNeiborNodeId(Map<Integer, Double> neiborNodeId) {
		this.neiborNodeId = neiborNodeId;
	}

	/**
	 * 获取所有与当前节点相邻的所有节点
	 * 
	 * @return
	 */
	public Set<Integer> getAllNeibor() {
		// System.out.println("邻居节点有：" + neiborNodeId.keySet());
		return neiborNodeId.keySet();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + "--" + neiborNodeId;
	}

	@Override
	public int compareTo(Node object) {
		Node node = (Node) object;
		if (this.id > node.getId()) {
			return 1;
		} else if (this.id < node.getId()) {
			return -1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		map.put(2, 3.0);
		map.put(3, 3.0);
		map.put(4, 3.0);
		map.put(5, 3.0);

		Node node1 = new Node();
		node1.setNeiborNodeId(map);
		node1.getAllNeibor();
	}

}
