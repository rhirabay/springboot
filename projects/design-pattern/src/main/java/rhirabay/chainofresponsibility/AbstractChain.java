package rhirabay.chainofresponsibility;

public abstract class AbstractChain {
    // chainする次のクラスを保持
    protected AbstractChain next = null;

    // nextを登録する
    public AbstractChain setNext(AbstractChain next) {
        this.next = next;
        return this;
    }

    // 処理順を定義するために、getOrderを実装させる
    public abstract int getOrder();

    // 業務ロジック
    // 自身の業務が終わったらnext.chain()を呼び出す
    // 後続の業務が不要な場合はreturnする
    public abstract void chain();
}
