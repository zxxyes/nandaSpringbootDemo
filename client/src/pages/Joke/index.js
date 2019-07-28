import React from 'react'
import { connect } from 'dva'
import { List, Card, Button, Input, Row, Col } from 'antd'

import styles from './index.css'


class App extends React.Component {
  render() {

    const { current, list } = this.props.joke
    const { TextArea } = Input

    const createCard = (
      <Card title="创建新卡片">
        <Input ref={ref => this.titleInput = ref} />
        <TextArea ref={ref => this.contentInput = ref} />
        <Button onClick={this.createHandler}>创建</Button>
      </Card>
    )

    const editCard = (
      <Card title="编辑卡片" key={current.id}>
        <Input ref={ref => this.titleEdit = ref} defaultValue={current.title} />
        <TextArea ref={ref => this.contentEdit = ref} defaultValue={current.content} />
        <Button onClick={this.updateHandler}>保存</Button>
      </Card>
    )

    const topBar = (
      <Row>
        <Col span={12}>{createCard}</Col>
        <Col span={12}>{editCard}</Col>
      </Row>
    )

    const jokeItemList = <List className={styles.jokeItemList} grid={this.grid} dataSource={list} renderItem={this.jokeItemParser} />

    return (
      <Card>
        {topBar}
        {jokeItemList}
      </Card>
    )
  }

  componentDidMount() { this.props.dispatch({ type: "joke/fetchJokeList" }) }

  constructor(props) {
    super(props)

    /* 响应式栅格布局 */
    this.grid = {
      gutter: 8,
      xs: 1,
      sm: 2,
      md: 4,
      lg: 4,
      xl: 6,
      xxl: 3,
    }
  }

  jokeItemParser = joke => (
    <List.Item>
      <Card title={joke.title} actions={[
        <Button icon="edit" data-id={joke.id} onClick={e => this.editHandler(e.target.dataset.id)} />,
        <Button icon="delete" data-id={joke.id} onClick={e => this.deleteHandler(e.target.dataset.id)} />
      ]}>
        <p>{joke.content}</p>
      </Card>
    </List.Item>
  )

  createHandler = e => {
    const title = this.titleInput.state.value
    const content = this.contentInput.textAreaRef.value

    this.props.dispatch({
      type: "joke/newJoke",
      body: { title, content }
    })
  }

  updateHandler = e => {
    const id = this.props.joke.current.id
    const title = this.titleEdit.state.value
    const content = this.contentEdit.textAreaRef.value

    this.props.dispatch({
      type: "joke/updateJoke",
      id,
      body: { title, content }
    })
  }

  editHandler = id => this.props.dispatch({ type: "joke/fetchJoke", id })
  deleteHandler = id => this.props.dispatch({ type: "joke/deleteJoke", id })
}

export default connect(({ joke }) => ({ joke }))(App)
