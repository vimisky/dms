package com.vimisky.dms.paging;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * ҳ����Ĭ����.<br>
 * ֮���Էֳ�Chunk��PageImpl�����࣬����ΪChunk�а�װ�����ɵ��������ɵ�Pageable�ӿ��ܹ����ص���Ϣ�Լ�content����PageImpl���ɷ�ҳ����DAO���ص���Ϣ����Ҫ��total��hasNext��Ϣ��
 * ��֪����ôдע���Ƿ��ܹ�˵���Լ�
 * */
public class PageImpl<T> extends Chunk<T> implements Page<T> {
	/**
	 * ���л�ID��eclipse�������Զ�����
	 */
	private static final long serialVersionUID = 825069709849906256L;
	
	/**
	 * ����Ԫ������
	 * */
	private final int total;
	/**
	 * �Ƿ�����һҳ,û���������
	 * */
//	private boolean hasNext;
	/**
	 * ��ҳ�������final���Σ�chunk��PageImpl�����Լ���pageable
	 * */
	private final Pageable pageable;
	/**
	 * ���Ĺ��췽��.����{@link PageImpl}ʵ��
	 * @param content
	 * @param pageable
	 * @param total
	 * */
	public PageImpl(List<T> content, Pageable pageable, int total){
		super(content, pageable);
		//����
		assert total >= content.size();
		this.total = total;
		this.pageable = pageable;
	}
	
	/**
	 * ����ҳ������
	 * */
	@Override
	public int getTotalPages() {
		// TODO Auto-generated method stub
		//Math.ceil()������closest�����ڵ���
		return getSize() == 0 ? 1 : (int)Math.ceil((double)total/(double)getSize());
	}

	/**
	 * ����Ԫ������
	 * */
	@Override
	public int getTotalElements() {
		// TODO Auto-generated method stub
		return total;
	}

	/**
	 * �ж��Ƿ�����һҳ.
	 * ʹ����ͨ��ѧ�����ڲ�������߼�
	 * */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		//totalpages ������ҳ���1,ҳ���Ǵ�0��ʼ
		return getNumber()+1<getTotalPages();
	}

	/**
	 * ת��ΪMap
	 * */
	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
		// TODO Auto-generated method stub
		return new PageImpl<S>(getConvertedContent(converter), pageable, total);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (total^total>>>32);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		PageImpl<?> that = (PageImpl<?>) obj;
		return total == that.total && super.equals(that);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String contentType = "UNKNOWN";
		List<T> content = content();

		if (content.size() > 0) {
			contentType = content.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total = -10002;
		System.out.println("result:" + (total^total>>32));
		System.out.println("total >>> 1 :" + (total>>2));
		System.out.println("total >>> 1 :" + (total>>>1));
	}
}
