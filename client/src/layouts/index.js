import styles from './index.css';

function BasicLayout(props) {
  return (
    <div className={styles.normal}>
      <p className={styles.title}>this is a modern-app-demo</p>
      {props.children}
    </div>
  );
}

export default BasicLayout;
