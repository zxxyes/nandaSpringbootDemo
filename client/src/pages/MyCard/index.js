import React from 'react';

import { List,Card} from 'antd';

class Demo extends React.Component {
  render() {

    return (
      <dev>
      <Card title="Default size card" extra={<a href="#">More</a>} style={{ width: 300 }}>
      <p>Card content</p>
      <p>Card content</p>
      <p>Card content</p>
    </Card>
    <Card size="small" title="Small size card" extra={<a href="#">More</a>} style={{ width: 300 }}>
      <p>Card content</p>
      <p>Card content</p>
      <p>Card content</p>
    </Card>
    </dev>
    )

  }




}


export default Demo;
